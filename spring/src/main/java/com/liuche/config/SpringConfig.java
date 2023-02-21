package com.liuche.config;

import com.liuche.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.liuche.service")
@Configuration
public class SpringConfig {
    @Bean
    public UserService userService() { // userService这个相当于bean的名称
        return new UserService();
    }
}
