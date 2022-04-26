package com.example.springapp.utils;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenUtils {

	public static final long JWT_TOKEN_VALIDITY = 2 * 60 * 60;
	
//	@Value("${jwt.secret}")
	public String secret = "bait_and_tackle";
	
//	@SuppressWarnings("finally")
	public HashMap<String, Object> generateToken(HashMap<String, Object> claims) {
		HashMap<String, Object> tokenObject = new HashMap<String, Object>();
		try {
			String token =  doGenerateToken(claims);
			tokenObject.put("token", token);
			tokenObject.put("error", null);
		} catch (Exception e) {
			tokenObject.put("token", null);
			tokenObject.put("error", "error creating the token");
		}
		return tokenObject;
		
	}
	
	
	private String doGenerateToken(HashMap<String, Object> claims) {
		
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	
	public Object getClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
}
