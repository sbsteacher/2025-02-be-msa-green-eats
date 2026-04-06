package com.green.eats.user.application.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSigninReq {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
