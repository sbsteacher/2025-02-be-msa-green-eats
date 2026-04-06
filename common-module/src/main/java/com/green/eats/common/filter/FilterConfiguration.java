package com.green.eats.common.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfiguration {
    /*
    name: application.yml에서 찾을 속성 이름입니다. (green-eats.filter.snake-to-camel.enabled)
    havingValue = "true": 해당 속성값이 정확히 true일 때만 필터를 활성화합니다.
    matchIfMissing = true: 이게 중요합니다! 학생들이 yml에 아무런 설정을 하지 않았더라도, 기본적으로 이 필터를 작동시키겠다는 의미입니다. 만약 기능을 끄고 싶을 때만 명시적으로 false를 적으면 됩니다.
    */
    @Bean
    @ConditionalOnProperty(name = "settings.filter.snake-to-camel.enabled", havingValue = "true", matchIfMissing = true)
    public FilterRegistrationBean<QueryStringSnakeToCamelFilter> snakeToCamelFilter() {
        FilterRegistrationBean<QueryStringSnakeToCamelFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new QueryStringSnakeToCamelFilter());
        registrationBean.addUrlPatterns("/*"); // 모든 요청에 적용
        return registrationBean;
    }
}
