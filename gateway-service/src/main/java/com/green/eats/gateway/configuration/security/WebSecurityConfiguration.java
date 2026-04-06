package com.green.eats.gateway.configuration.security;

import com.green.eats.gateway.configuration.filter.TokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration 애노테이션 아래에 있는 @Bean은 무조건 싱글톤이다.
@Configuration
@RequiredArgsConstructor
public abstract class WebSecurityConfiguration {
    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) ) //시큐리티에서 session사용 않겠다.
                .httpBasic( hb -> hb.disable() )  //시큐리티에서 제공해주는 로그인 화면이 있는데 사용하지 않겠다
                .formLogin( fl -> fl.disable() ) //어차피 BE가 화면을 만들지 않기 때문에 formLogin기능도 비활성화하겠다.
                .csrf( csrf -> csrf.disable() ) //어차피 BE가 화면을 만들지 않으면 csrf 공격이 의미가 없기 때문에 비활성화하겠다.
                //인가처리 (권한처리)

                //아래 내용은 (POST) /api/board 로 요청이 올 때는 반드시 로그인이 되어있어야 한다.
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
