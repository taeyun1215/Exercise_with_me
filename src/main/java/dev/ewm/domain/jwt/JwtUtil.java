package dev.ewm.domain.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dev.ewm.domain.user.request.UserLoginRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil{
	@Value("${jwt.token.secret}")
	private String secretKey;
	
	private long expTime = 60 * 60 * 1000L;

	public String createToken(UserLoginRequest userLoginRequest) {
//		if(expTime<=0) {
//			throw new RuntimeException("0이상 입력");
//		}
//
		Claims claims = Jwts.claims();
		claims.put("username", userLoginRequest.getUsername());
		SignatureAlgorithm signatue = SignatureAlgorithm.HS256;
		byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(secretKey);
		Key signingKey = new SecretKeySpec(secretKeyBytes, signatue.getJcaName());

		log.info("key: {}", secretKey);
		return Jwts.builder()
//				.setSubject(userLoginRequest.getUsername())
				.setClaims(claims)
				.signWith(signingKey, signatue)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expTime))
				.compact();
	}
	
	public boolean isExpired(String token, String secretKey) {
		return Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getExpiration()
				.before(new Date());
	}
}
