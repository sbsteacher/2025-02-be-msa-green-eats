package com.green.eats.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;
//Spring Security가 인증처리를 할 때 사용하는 객체
@Slf4j
@Getter
@RequiredArgsConstructor
@ToString
public class UserPrincipal implements UserDetails {
    private final JwtUser jwtUser;

    public long getSignedUserId() {
        return jwtUser.getSignedUserId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleName = "ROLE_" + jwtUser.getUserRole().name();
        log.info("roleName: {}", roleName);
        return List.of(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
