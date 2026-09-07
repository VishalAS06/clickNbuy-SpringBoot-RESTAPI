package com.jsp.clinkNBuy.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.jsp.clinkNBuy.exception.GlobalExceptionHandler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtUtil  {
	
	private final Key key;
	
	public JwtUtil(@Value("${jwt.secret}") String secretkey, GlobalExceptionHandler globalExceptionHandler) {
		this.key=Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretkey));
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> Claims =new HashMap<>();
		String role=userDetails
				.getAuthorities()
				.iterator()
				.next()
				.getAuthority();
				Claims.put("role", role);

	return Jwts.builder()
			.addClaims(Claims)
			.setSubject(userDetails.getUsername())
			.setIssuedAt(new Date(System.currentTimeMillis()))
			
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))//1 hour
			.signWith(key).compact();
	}

	public String extractUsername(String token) {
		
		return extractAllClaims(token).getSubject() ;
	}
	
	
	public SimpleGrantedAuthority extractRole(String token) {
		Claims claims=extractAllClaims(token);
		String role= claims.get("role",String.class);
		return new SimpleGrantedAuthority(role);
	}

	public Date extractExiration(String token) {
		return extractAllClaims(token).getExpiration();
	}
	

	public boolean validateToken(String token, UserDetails userDetails) {
		
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		
		return extractExiration(token).before(new Date());
	}
	
	
	private Claims extractAllClaims(String token) {
		
		return  Jwts.parser()
				.setSigningKey(key)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

}
