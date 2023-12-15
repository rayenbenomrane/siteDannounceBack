package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
@Configuration
public class PasswordBean {
	@Bean
    public org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder BCryptPasswordEncoder(){
    return  new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
}

}