package com.example.wahwah.member;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import com.example.wahwah.WebSecurityConfig;
import com.example.wahwah.hospitaluser.AuthFailureHandler;
import com.example.wahwah.hospitaluser.AuthSuccessHandler;
import com.example.wahwah.hospitaluser.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@Order(2)
public class HospitalUserWebSecurityConfig extends WebSecurityConfig {
	private final AuthSuccessHandler authSuccessHandler;

	// 생성자 생성 방식으로 의존성 주입
	// 생성자 생성 방식으로 의존성 주입

	private final AuthFailureHandler authFailureHandler;
	private final UserDetailsServiceImpl userDetailsService;

	// 스프링시큐리티에서 암호화 스프링빈 등록

	// 스프링시큐리티 적용 제외 대상 설정 스프링빈 등록
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/assets/**", "/js/**", "/vendor/**","/images/**", "/css/**", "/profile/**");
	}

	@Order(2)
	@Bean
	public SecurityFilterChain MasterfilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/master/**");

		http
				.formLogin(login -> login
						.usernameParameter("hospitalUserId")
						.loginPage("/master/login")
						.successHandler(authSuccessHandler)
						.failureHandler(authFailureHandler));
		http
				.rememberMe(me -> me
						.key("diamond")
						.alwaysRemember(false)
						.tokenValiditySeconds(3600 * 24 * 7)
						.rememberMeParameter("remember-me")
						.userDetailsService(userDetailsService)
						.authenticationSuccessHandler(authSuccessHandler));

		http
				.authorizeHttpRequests(auth -> auth.requestMatchers("/master/**").permitAll()
						.anyRequest().authenticated());

		http
				.sessionManagement(management -> management
						.maximumSessions(1)
						.maxSessionsPreventsLogin(false)
						.expiredUrl("/master/login"));

		http
				.logout(logout -> logout
						.logoutUrl("/master/logout")
						.logoutSuccessUrl("/master/login")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID", "remember-me")
						.permitAll());

		// CSRF, CORS 공격 방어 비활성화
		http
				.csrf((csrf) -> csrf.disable())
				.cors((cors) -> cors.disable());

		System.out.println("스프링 시큐리티 설정 완료 니니즈");
		return http.build(); // SecurityFilterChain 객체를 반환합니다.
	}
}
