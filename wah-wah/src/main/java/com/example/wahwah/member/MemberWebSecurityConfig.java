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
public class MemberWebSecurityConfig extends WebSecurityConfig {

	private final OAuth2FailureHandler oAuth2FailureHandler;
	private final OAuth2SuccessHandler oAuth2SuccessHandler;

	// 스프링 시큐리티 로그인, 권한부여, 로그아웃 등의 설정 작업하는 스프링빈 등록

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

		// 		 				http
		// 		 .authorizeHttpRequests(auth -> auth
		// 		 		.requestMatchers("/kids/login").permitAll()
		// 		// 		// .requestMatchers("/master/**").permitAll()
		// 		// 		// .requestMatchers("**").permitAll()
		// 		// 		// .requestMatchers("/board/**").hasAnyAuthority("USER","MASTER")
		// 		// 		// .requestMatchers("/master/**").hasAnyAuthority("MASTER")
		// 		 		.anyRequest().authenticated());
		// // 접근권한 설정(권한 부여 : Authorization)
		http
				.authorizeHttpRequests(auth -> auth.requestMatchers("/master/**").permitAll().requestMatchers("/kids/login").permitAll().anyRequest().authenticated());
				// .requestMatchers("/master/**").permitAll()
				// .requestMatchers("/kids/**").permitAll();
				
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

	@Order(1)
	@Bean
	public SecurityFilterChain MemberFilterChain(HttpSecurity http) throws Exception {

		// CSRF, CORS 공격 방어 비활성화
		http
				.csrf((csrf) -> csrf.disable())
				.cors((cors) -> cors.disable());

		// OAuth2 인증설정 -> 외부 사이트 로그인 기능을 이용하기 위한 API
		http.oauth2Login((login) -> login
				.loginPage("/kids/login") // 인증에 실패했을때 이동하는 페이지
				.successHandler(oAuth2SuccessHandler)
				.failureHandler(oAuth2FailureHandler));

		// 접근권한 설정(권한 부여 : Authorization)
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/kids/**").permitAll()
						.requestMatchers("/master/login").permitAll()
						// .requestMatchers("/master/**").permitAll()
						// .requestMatchers("**").permitAll()
						// .requestMatchers("/board/**").hasAnyAuthority("USER","MASTER")
						// .requestMatchers("/master/**").hasAnyAuthority("MASTER")
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
