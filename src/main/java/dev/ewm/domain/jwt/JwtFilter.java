package dev.ewm.domain.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import dev.ewm.domain.user.User;
//import dev.ewm.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
//	private final UserService userService;
	private final String secretKey;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		//token안보내면 block
		if(authorization==null || !authorization.startsWith("ewm ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String refreshAuthorization = request.getHeader("RefreshAuthorization");
		String token = authorization.replace("ewm ", "");
		String refresh = refreshAuthorization.replace("ewm ", "");
		JwtUtil jwtUtil = new JwtUtil();
		User user = jwtUtil.getUser(token, secretKey);
		
		//토큰이 expired되어있는지 여부
		if(jwtUtil.isExpired(token, secretKey)) {
			log.error("엑세스 토큰 만료");
			
			if(!jwtUtil.isExpired(refresh, secretKey)) {
				response.setHeader("Authorization", new StringBuilder("ewm ").append(jwtUtil.createToken(user, 30 * 60 * 1000L, secretKey)).toString());
//				response.setHeader("Authorization", new StringBuilder("ewm ").append(jwtUtil.createToken(userName, 30 * 60 * 1000L, secretKey)).toString());
			} else {
				log.error("리프레쉬 토큰 만료");
				filterChain.doFilter(request, response);
				return;
			}
		} else {
			response.setHeader("Authorization", authorization);
		}
		
		response.addHeader("RefreshAuthorization", jwtUtil.checkRefresh(refresh, secretKey, user));
		
		List<SimpleGrantedAuthority> simpleGrantedAuthority = new ArrayList<>();
		simpleGrantedAuthority.add(new SimpleGrantedAuthority("USER"));
		
		//권한 부여
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(user.getUsername(), null, simpleGrantedAuthority);
		
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(request, response);
	}

}
