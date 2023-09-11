package com.company.user.util

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import java.util.concurrent.TimeUnit


@Service
class JwtUtil {

    @Value("\${jwt.secret}")
    private val jwtSecret: String? = null

    @Value("\${jwt.expiration}")
    private val jwtExpiration: Long? = null

    fun generateToken(username: String, role: String): String {
        val claims: Map<String, Any?> = mapOf("role" to role)
        return createToken(claims, username, jwtExpiration!!)
    }

    private fun createToken(claims: Map<String, Any?>, subject: String, expireTime: Long): String {
        return Jwts.builder().setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(expireTime)))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun getClaim(token: String?, claim: String): String {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).body[claim].toString()
    }

    fun getSubject(token: String?): String {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).body.subject
    }

    fun validateJwtToken(authToken: String?): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(authToken)
            true
        } catch (e: MalformedJwtException) {
            println("JwtUtils | validateJwtToken | Invalid JWT token: {}"+ e.message)
            false
        } catch (e: ExpiredJwtException) {
            println("JwtUtils | validateJwtToken | JWT token is expired: {}"+ e.message)
            false
        } catch (e: UnsupportedJwtException) {
            println("JwtUtils | validateJwtToken | JWT token is unsupported: {}" + e.message)
            false
        } catch (e: IllegalArgumentException) {
            println("JwtUtils | validateJwtToken | JWT claims string is empty: {}"+ e.message)
            false
        }
    }

    private fun getKey(): Key? {
        val key = Decoders.BASE64.decode(jwtSecret)
        return Keys.hmacShaKeyFor(key)
    }

}