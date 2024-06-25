package com.example.skhuthon_0th_team10.config;

import com.example.skhuthon_0th_team10.jwt.JwtFilter;
import com.example.skhuthon_0th_team10.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .anyRequest().permitAll() // 일단 전부 열어 둠
//                        .requestMatchers("/api/oauth2/**").permitAll() // 모두 허용
//                        .requestMatchers("/websocket/**").permitAll()
//                        .requestMatchers("/qr").permitAll()
//                        .requestMatchers("/static/js/**","/static/css/**","/static/img/**"
//                                ,"/swagger-ui/**","/api-docs/**").permitAll() // 스웨거는 인증 피함
//                        .requestMatchers("**").authenticated() // 모두 허용
//                        .requestMatchers("/post/**").authenticated() // 권한 있는 사용자만
                )
//                .cors(cors -> cors.configurationSource(configurationSource()))
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    // 토큰을 모든 서비스에 보내주니까, 토큰으로 파싱해서 원하는 컬럼값 가져오기.  Principal은 토큰 안의 정보를 가져올 때?
//    @Bean
//    public CorsConfigurationSource configurationSource() { // cors 설정 메서드
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.setAllowedOriginPatterns(List.of("*"));
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setExposedHeaders(List.of("Access-Control-Allow-Credentials", "Authorization", "Set-Cookie"));
//        configuration.setAllowCredentials(true);
//        configuration.setMaxAge(3600L);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }

}