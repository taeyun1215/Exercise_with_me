package mate.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf 보안 설정 끄기
        // 토큰 방식, 즉 stateless 기반 인증에선 서버에서 인증 정보를 보관하지 않기 때문에
        // csrf 공격에 안전하고 매번 csrf 토큰을 받기 않기 때문에 불필요)
        http.csrf().disable();

        // 스프링 시큐리티가 세션을 생성하지 않고 기존 세션을 사용하지도 않음(JWT 사용을 위함)
        http
                .authorizeRequests().antMatchers("/").permitAll();

        return http.build();
    }
}
