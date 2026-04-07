package com.green.eats.user.application;


import com.green.eats.common.model.JwtUser;
import com.green.eats.common.model.ResultResponse;
import com.green.eats.common.security.JwtTokenManager;
import com.green.eats.user.application.model.UserSigninReq;
import com.green.eats.user.application.model.UserSigninRes;
import com.green.eats.user.application.model.UserSignupReq;
import com.green.eats.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final JwtTokenManager jwtTokenManager;

    @PostMapping("/signup")
    public ResultResponse<?> signup(@RequestBody UserSignupReq req) {
        log.info("req: {}", req);
        userService.signup(req);
        return ResultResponse.builder()
                .resultMessage("회원가입 성공")
                .resultData(1)
                .build();
    }

    @PostMapping("/signin")
    public ResultResponse<?> signin(HttpServletResponse res, @RequestBody UserSigninReq dto) {
        User signedUser = userService.signin(dto);

        //보안 쿠키 처리
        JwtUser jwtUser = new JwtUser( signedUser.getId(), signedUser.getName(), signedUser.getRole() );
        jwtTokenManager.issue(res, jwtUser);

        UserSigninRes userSigninRes = UserSigninRes.builder()
                .id( signedUser.getId() )
                .name(signedUser.getName() )
                .build();

        return ResultResponse.builder()
                .resultMessage("로그인 성공")
                .resultData(userSigninRes)
                .build();
    }

    @PostMapping("/signout")
    public ResultResponse<?> signOut(HttpServletResponse res) {
        jwtTokenManager.signOut(res);
        return new ResultResponse<>("로그아웃 성공", 1);
    }

    @PostMapping("/reissue")
    public ResultResponse<?> reissue(HttpServletResponse res, HttpServletRequest req) {
        jwtTokenManager.reissue(req, res);
        return new ResultResponse<>("AT 재발행", null);
    }
}
