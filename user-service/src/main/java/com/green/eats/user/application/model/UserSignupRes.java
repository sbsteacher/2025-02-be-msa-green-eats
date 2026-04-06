package com.green.eats.user.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserSignupRes {
    private Long id;
    private String email;
    private String name;
    private String address;
}
