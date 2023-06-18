package com.example.wahwah;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	//스프링시큐리티에서 암호화 스프링빈 등록
	@Bean
	BCryptPasswordEncoder PasswordEncode() {
		return new BCryptPasswordEncoder();
	}

}
