package com.example.todo.securityConfig;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.todo.exception.APIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenGenerator {
	public String generateToken(Authentication authentication) {
		String email=authentication.getName();
		Date currentdate=new Date();
//		3600000
		Date expired=new Date(currentdate.getTime()+172800000);
		String token=Jwts.builder()
					.setSubject(email)
					.setIssuedAt(currentdate)
					.setExpiration(expired)
					.signWith(SignatureAlgorithm.HS512,"luckykey")
					.compact();

		return token;
	}
	public String getEmailfromToken(String token){
		Claims claims=Jwts.parser().setSigningKey("luckykey").parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public boolean validateToken(String token){
		try {
			//if it is valid token we will decode it
			Jwts.parser().setSigningKey("luckykey").parseClaimsJws(token);
			return true;
		}
		catch(Exception e) {
			throw new APIException("Token Issue"+e.getMessage());
		}
	}
}

