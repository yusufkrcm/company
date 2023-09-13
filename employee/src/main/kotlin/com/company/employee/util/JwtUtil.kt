package com.company.employee.util

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*


@Service
class JwtUtil (private val request: HttpServletRequest) {

    @Value("\${jwt.secret}")
    private val jwtSecret: String? = null

    fun getJwtFromRequest(): String? {
        val header: String? = request.getHeader("Authorization")
        return if (header != null && header.startsWith("Bearer ")) {
            header.substring(7)
        } else null
    }

    fun getClaim(token: String?, claim: String): String? {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).body[claim].toString()
    }

    fun getSubject(token: String?): String? {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).body.subject
    }

    private fun getKey(): Key? {
        val key = Decoders.BASE64.decode(jwtSecret)
        return Keys.hmacShaKeyFor(key)
    }


}