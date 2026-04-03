package com.green.eats.order;

import net.datafaker.Faker;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.Locale;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//Test는 트랜잭션으로 작동하며 테스트가 끝나면 Rollback 시킨다.
// 그런데 우리는 실제로 데이터를 insert해야하기 때문에 Rollback을 끈다.
@Rollback(false)
public abstract class Dummy {
    protected Faker koFaker = new Faker(Locale.KOREAN); //한글
    protected Faker enFaker = new Faker(Locale.ENGLISH); //영어
}
