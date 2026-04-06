package com.green.eats.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

//JWT를 만들 때 payload에 담을 객체
@Getter
@AllArgsConstructor
public class JwtUser {
    private Long signedUserId;
    private String name;
    private UserRole userRoles;
}
