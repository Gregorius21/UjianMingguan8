package com.juaracoding.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable{

	private static final long serialVersionUID = 8535593094115651238L;
	public static final long JWT_TOKEN_VALIDITY = 5*60*60;
		
	@Value("${jwt.secret}")
	private String secret;
	
//	menerima username dari jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
//	menerima
	public String getNameFromToken(String token) {
		return getAllClaimsFromToken(token).get("name").toString();
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
//	untuk dapetin informasi dari si token dengan secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
//	mendapatkan tanggal kadaluarsa dari jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
//	ngecek token kadaluarsa
	private boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
//	generate token untuk user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		String token = Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY *1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		return token;
	}
	
//	validasi token
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token));
	}
	

}



