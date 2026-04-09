package com.green.eats.user.application;

import com.green.eats.common.event.UserEvent;
import com.green.eats.common.model.UserRole;
import com.green.eats.user.application.model.UserSigninReq;
import com.green.eats.user.application.model.UserSignupReq;
import com.green.eats.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public void signup(UserSignupReq req) {
        String hashedPw = passwordEncoder.encode( req.getPassword() );
        log.info("hashedPw: {}", hashedPw);

        //회원가입한 유저의 id값을 얻어오고 싶다.
        User newUser = new User();
        newUser.setEmail( req.getEmail() );
        newUser.setPassword( hashedPw );
        newUser.setName( req.getName() );
        newUser.setRole( UserRole.USER );
        userRepository.save(newUser);

        // Kafka로 회원가입 이벤트 발행
        UserEvent event = new UserEvent(newUser.getId(), newUser.getName(), "CREATE");
        kafkaTemplate.send("user-topic", String.valueOf(newUser.getId()), event);
    }

    public User signin(UserSigninReq req) {
        User user = userRepository.findByEmail( req.getEmail() );
        log.info("user: {}", user);
        if(user == null || !passwordEncoder.matches(req.getPassword(), user.getPassword())) { //비밀번호가 맞지 않으면?
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "아이디, 비밀번호를 확인해 주세요.");
        }

        return user;
    }



}
