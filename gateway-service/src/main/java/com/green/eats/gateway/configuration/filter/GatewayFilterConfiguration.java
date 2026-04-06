package com.green.eats.gateway.configuration.filter;

import com.green.eats.common.model.JwtUser;
import com.green.eats.common.model.UserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GatewayFilterConfiguration {
    @Bean
    public HandlerFilterFunction<ServerResponse, ServerResponse> authHeaderFilter() {
        return (request, next) -> {
            // 1. 제외 조건 처리 (user-service는 헤더 추가 없이 통과)
            // 요청 경로가 /api/user 로 시작하는 경우 바로 다음 단계로 넘깁니다.
            if (request.uri().getPath().startsWith("/api/user")) {
                return next.handle(request);
            }

            // 2. 인증 정보 추출 (강사님이 작성하신 로직 활용)
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof UserPrincipal userPrincipal) {
                // 3. 헤더를 추가하여 새로운 요청 객체 생성
                JwtUser jwtUser = userPrincipal.getJwtUser();

                ServerRequest modifiedRequest = ServerRequest.from(request)
                        .header("X-User-Id", String.valueOf( jwtUser.getSignedUserId() ))
                        .header("X-User-Name", jwtUser.getName())
                        .build();

                // 수정된 요청으로 다음 단계(실제 서비스 호출) 진행
                return next.handle(modifiedRequest);
            }

            // 인증 정보가 없으면 원래 요청 그대로 진행
            return next.handle(request);
        };
    }
}
