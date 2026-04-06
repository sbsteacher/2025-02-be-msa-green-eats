package com.green.eats.user.application.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//회원가입 때 FE로 넘어오는 데이터를 담기위한 클래스
@Getter
@Setter
@ToString
public class UserSignupReq {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    private String address;
}
