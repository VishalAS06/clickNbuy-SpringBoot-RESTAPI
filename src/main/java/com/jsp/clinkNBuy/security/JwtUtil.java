package com.jsp.clinkNBuy.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtUtil  {
	
	private final String SECRET_KEY;
	
	public JwtUtil(@Value("${jwt.secret}") String secretkey) {
		SECRET_KEY=secretkey;
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> Claims =new HashMap<>();
		return createToken(Claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+1000*60*60))//1 hour ecpiry
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public String extractUsername(String token) {
		
		return extractClaim(token, Claims::getSubject) ;
	}

	

	

	private <T> Object extractClaim(String token, Fuction<Claims, T> claimsResolver) {
		final Claims claims=(Claims) Jwts.parser().setSigningKey(SECRET_KEY).parse(token).getBody();
		return claimsResolver.apply(claims);
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		
		final String username = extractUsername(token);
		return (username.equals(userDetails).getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		
		return extractExpiration(token).befo;
	}

}
