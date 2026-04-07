package com.green.eats.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

//JWT를 만들 때 payload에 담을 객체
@Getter
@AllArgsConstructor
@ToString
public class JwtUser {
    private Long signedUserId;
    private String name;
    private UserRole userRole;
}
