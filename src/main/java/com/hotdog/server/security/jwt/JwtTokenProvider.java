package com.hotdog.server.security.jwt;



import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hotdog.server.web.dto.UserLoginFormDTO;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	private final String key;
	private final Long validTime;

	public JwtTokenProvider(@Value("${jwt.secret}") String key, @Value("${jwt.validTime}") Long validTime) {
		this.key = key;
		this.validTime = validTime;
	}

	public String createToken(UserLoginFormDTO userLoginFormDTO) {
		long expiryTime = validTime * 1000L;
		Claims claims = Jwts.claims().setSubject(userLoginFormDTO.getEmail());
		Date now = new Date();
		return Jwts.builder()
			.setClaims(claims)
			.signWith(SignatureAlgorithm.HS512, key)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + expiryTime))
			.compact();
	}

	public String validateAndGetEmail(String token)
	{
		Claims claims = Jwts.parser()
			.setSigningKey(key)
			.parseClaimsJws(token)
			.getBody();
		return claims.getSubject();
	}



}
