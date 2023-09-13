package com.company.user.util

import com.company.user.model.exception.JwtNotValid
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class JwtUtil(private val request: HttpServletRequest) {

    @Value("\${jwt.secret}")
    private val jwtSecret: String? = null

    @Value("\${jwt.expiration}")
    private val jwtExpiration: Long? = null

    fun generateToken(subject: String, role: String): String {
        val claims: Map<String, Any?> = mapOf("role" to role)
        return createToken(claims, subject, jwtExpiration!!)
    }

    private fun createToken(claims: Map<String, Any?>, subject: String, expireTime: Long): String {
        val now = System.currentTimeMillis()
        return Jwts.builder().setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(now))
            .setExpiration(Date(now + TimeUnit.HOURS.toMillis(expireTime)))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun getClaim(token: String?, claim: String): String? {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).body[claim].toString()
    }

    fun getSubject(token: String?): String? {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).body.subject
    }

    fun validateJwtToken(authToken: String?): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(authToken)
            true
        }catch (e: JwtException){
            throw JwtNotValid(e.message)
        }
    }

    private fun getKey(): Key? {
        val key = Decoders.BASE64.decode(jwtSecret)
        return Keys.hmacShaKeyFor(key)
    }


    fun getJwtFromRequest(): String? {
        val header: String? = request.getHeader("Authorization")
        return if (header != null && header.startsWith("Bearer ")) {
            header.substring(7)
        } else null
    }

}