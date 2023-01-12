package dev.ewm.domain.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
//import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//import dev.ewm.domain.user.request.UserLoginRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
	@Value("${jwt.token.secret}")
	private String secretKey;
	
	public String createToken(String username, long expTime) {
//		public String createToken(UserLoginRequest userLoginRequest, long expTime) {
		if(expTime<=0) {
			throw new RuntimeException("0이상 입력");
		}

		Claims claims = Jwts.claims();
//		claims.put("username", userLoginRequest.getUsername());
		claims.put("username", username);
		SignatureAlgorithm signatue = SignatureAlgorithm.HS256;
//		byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);
		Key signingKey = new SecretKeySpec(secretKey.getBytes(), signatue.getJcaName());

//		log.info("key: {}", secretKey);
		return Jwts.builder()
//				.setSubject(userLoginRequest.getUsername())
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
//			log.info("bool: {}", bool);
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
}
