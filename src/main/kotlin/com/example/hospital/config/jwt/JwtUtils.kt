package com.example.hospital.config.jwt

import com.example.hospital.model.UserDetailImp
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtUtils {
	@Value("\${app.jwtSecret}")
	private val jwtSecret: String? = null

	@Value("\${app.jwtExpirationMs}")
	private val jwtExpirationMs = 0
	fun generateJwtToken(authentication: Authentication): String {
		val userPrincipal: UserDetailImp = authentication.principal as UserDetailImp
		val a =  Jwts.builder().setSubject(userPrincipal.username)
			.setIssuedAt(Date())
			.setExpiration(Date(Date().time + jwtExpirationMs))
			.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, jwtSecret).compact().toString()

		return a
	}

	fun validateJwtToken(jwt: String?): Boolean {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt)
			return true
		} catch (e: MalformedJwtException) {
			System.err.println(e.message)
		} catch (e: IllegalArgumentException) {
			System.err.println(e.message)
		}
		return false
	}

	fun getUserNameFromJwtToken(jwt: String?): String {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).body.subject
	}
}