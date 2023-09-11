package com.company.user.interceptor

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException


@Component
class AuthFilter : OncePerRequestFilter() {

    @Throws(IOException::class, ServletException::class, ServletException::class)
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        try {
            val role = getHeaderByName(req ,"X-User-Roles")
            val username = getHeaderByName(req ,"X-User-Username")
            if (role != null && username != null) {
                val authorities: MutableList<SimpleGrantedAuthority> = ArrayList()
                    authorities.add(SimpleGrantedAuthority(role))

                val authentication = UsernamePasswordAuthenticationToken(
                    username, null, authorities
                )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(req)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            println("JwtAuthenticationFilter | doFilterInternal | e: $e")
        }
        chain.doFilter(req, res)
    }

    private fun getHeaderByName(request: HttpServletRequest , headerName: String): String? {
        val value = request.getHeader(headerName)
        return value ?: null
    }
}