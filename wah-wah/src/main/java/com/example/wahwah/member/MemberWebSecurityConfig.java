package com.example.wahwah.member;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import com.example.wahwah.WebSecurityConfig;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@Order(1)
public class MemberWebSecurityConfig extends WebSecurityConfig {

	private final OAuth2FailureHandler oAuth2FailureHandler;
	private final OAuth2SuccessHandler oAuth2SuccessHandler;

	// 스프링시큐리티 적용 제외 대상 설정 스프링빈 등록
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/kids/login", "/assets/**" ,"/fonts/**", "/js/**", "/vendor/**","/images/**", "/css/**", "/profile/**");
	}

	@Bean
	public SecurityFilterChain MemberFilterChain(HttpSecurity http) throws Exception {

		// CSRF, CORS 공격 방어 비활성화
		http
				.csrf((csrf) -> csrf.disable())
				.cors((cors) -> cors.disable());

		// OAuth2 인증설정 -> 외부 사이트 로그인 기능을 이용하기 위한 API
		http	
				.oauth2Login((login) -> login
				.loginPage("/kids/login") // 인증에 실패했을때 이동하는 페이지
				.successHandler(oAuth2SuccessHandler)
				.failureHandler(oAuth2FailureHandler));

		// 접근권한 설정(권한 부여 : Authorization)
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/kids/**", "/", "/**", "/fonts/**","/css/**","/assets/**","/vendor/**", "/js/**","/images/**").permitAll()
						.requestMatchers("/oauth2/authorization/**").permitAll()
						.anyRequest().authenticated());

		// 세션관리
		http
				.sessionManagement(management -> management
						.maximumSessions(1)
						.maxSessionsPreventsLogin(false)
						.expiredUrl("/kids/login"));

		// 로그아웃 처리
		http
				.logout(logout -> logout
						.logoutUrl("/kids/logout")
						.logoutSuccessUrl("/kids/login")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.permitAll());

		System.out.println("스프링 서큐리티 설정 완료");

		return http.build();
	}
}
