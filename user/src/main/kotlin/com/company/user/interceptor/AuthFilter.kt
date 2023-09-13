package com.company.user.interceptor

import com.company.user.model.exception.JwtNotValid
import com.company.user.util.JwtUtil
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
class AuthFilter(private val jwtUtil: JwtUtil) : OncePerRequestFilter() {

    @Throws(IOException::class, ServletException::class, ServletException::class)
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        try {
            val token = jwtUtil.getJwtFromRequest()
            if (!token.isNullOrEmpty()) {
                val role: String? = jwtUtil.getClaim(token, "role")
                val principal: String? = jwtUtil.getSubject(token)
                if (role != null && principal != null) {
                    val authorities: MutableList<SimpleGrantedAuthority> = ArrayList()
                    authorities.add(SimpleGrantedAuthority(role))

                    val authentication = UsernamePasswordAuthenticationToken(
                        principal, null, authorities
                    )
                    authentication.details = WebAuthenticationDetailsSource().buildDetails(req)
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
        } catch (e: Exception) {
            throw JwtNotValid(e.message)
        }
        chain.doFilter(req, res)
    }

}