package com.green.eats.common;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan("com.green.eats.common.constants")
public class CommonConfiguration {
}
