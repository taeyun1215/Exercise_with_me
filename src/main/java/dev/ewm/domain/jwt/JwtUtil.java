package dev.ewm.domain.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import dev.ewm.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
	
//	public String createToken(String username, long expTime, String secretKey) {
	public String createToken(User user, long expTime, String secretKey) {
		if(expTime<=0) {
			throw new RuntimeException("0이상 입력");
		}

		Claims claims = Jwts.claims();
		claims.put("username", user.getUsername());
		claims.put("id", user.getId());
		SignatureAlgorithm signatue = SignatureAlgorithm.HS256;
		Key signingKey = new SecretKeySpec(secretKey.getBytes(), signatue.getJcaName());

		return Jwts.builder()
				.setClaims(claims)
				.signWith(signingKey, signatue)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expTime))
				.compact();
	}
	
	public boolean isExpired(String token, String secretKey) {
		boolean bool = true;
		
		try {
			bool = Jwts.parserBuilder()
					.setSigningKey(secretKey.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody()
					.getExpiration()
					.before(new Date());
        } catch (Exception e) {
        	log.error("timeout");
        }
		
		return bool;
	}
	
//	public String getUserName(String token, String secretKey) {
	public User getUser(String token, String secretKey) {
		String username = "";
		Long id = 0L;
		
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(secretKey.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody();
			
//			username = Jwts.parserBuilder()
//					.setSigningKey(secretKey.getBytes())
//					.build()
//					.parseClaimsJws(token)
//					.getBody()
//					.get("username", String.class);
			username = claims.get("username", String.class);
			id = claims.get("id", Long.class);
        } catch (Exception e) {
        	log.error("error");
        }
		
		return User.builder()
				.username(username)
				.id(id)
				.build();
	}
	
//	public String checkRefresh(String refresh, String secretKey, String username) {
	public String checkRefresh(String refresh, String secretKey, User user) {
		return System.currentTimeMillis() - Jwts.parserBuilder()
				.setSigningKey(secretKey.getBytes())
				.build()
				.parseClaimsJws(refresh)
				.getBody()
				.getExpiration()
				.getTime()<1000*60*60*6L ? new StringBuilder("ewm ").append(createToken(user, 7 * 24 * 60 * 60 * 1000L, secretKey)).toString()  : refresh;
	}
}
