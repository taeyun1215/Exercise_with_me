package dev.ewm.domain.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
	
	public String createToken(String username, long expTime, String secretKey) {
		if(expTime<=0) {
			throw new RuntimeException("0이상 입력");
		}

		Claims claims = Jwts.claims();
		claims.put("username", username);
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
	
	public String getUserName(String token, String secretKey) {
		String username = "";
		
		try {
			username = Jwts.parserBuilder()
					.setSigningKey(secretKey.getBytes())
					.build()
					.parseClaimsJws(token)
					.getBody()
					.get("username", String.class);
        } catch (Exception e) {
        	log.error("error");
        }
		
		return username;
	}
	
	public String checkRefresh(String refresh, String secretKey) {
		return System.currentTimeMillis() - Jwts.parserBuilder()
				.setSigningKey(secretKey.getBytes())
				.build()
				.parseClaimsJws(refresh)
				.getBody()
				.getExpiration()
				.getTime()<1000*60*60*6L ? createToken(refresh, 7 * 24 * 60 * 60 * 1000L, secretKey) : refresh;
	}
}
