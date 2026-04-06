package com.green.eats.common;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.green.eats.common")
@ConfigurationPropertiesScan("com.green.eats.common.constants")
public class CommonConfiguration {
}
