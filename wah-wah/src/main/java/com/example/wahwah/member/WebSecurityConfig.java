package com.example.wahwah.member;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//import com.board.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
	
	// 의존성 주입. @RequiredArgsConstructor 사용해서 간단하게 의존성 주입(lombok)
//	private final AuthSuccessHandler authSuccessHandler;
	
	/* 생성자 생성 방식으로 의존성 주입
	 * private final AuthSuccessHandler authSuccessHandler;
	 * public WebSecurityConfig(AuthSuccessHandler authSuccessHandler) {
	 * 		this.authSuccessHandler = authSuccessHandler;
	 * }
	 */
	
//	private final AuthFailureHandler authFailureHandler;
//	private final UserDetailsServiceImpl userDetailsService;
	
	// 스프링시큐리티에서 암호화 스프링빈 등록
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 스프링시큐리티 적용 제외 대상 설정 스프링빈 등록
//	@Bean
//	WebSecurityCustomizer webSecurityCustomizer() {
//		
//		return (web)->web.ignoring().requestMatchers("/images/**", "/css/**", "/profile/**");
//	}
	
	// 스프링시큐리티 로그인, 권한부여, 로그아웃 등의 설정 작업하는 스프링빈 등록
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
/*		
		// 로그인 처리(인증 : Authentication)
		http
			.formLogin()
			.usernameParameter("userid")  // 아이디 변수명
			.loginPage("/user/login")  // 사용자가 지정한 로그인 화면 보여주는 URL
			.successHandler(authSuccessHandler)  // 성공시 요청을 처리할 핸들러. 이 핸들러는 com.board 패키지 안에 만들어줘야 한다.(AutoSuccessHandler.java)
			.failureHandler(authFailureHandler);  // 실패시 요청을 처리할 핸들러.
		
		// Remember-me 기능 활성화
		http
			.rememberMe()
			.key("diamond")  // 리눅스에서는 키 값으로 뭘 할 수 있다. 윈도우에서는 별 상관없음.
			.alwaysRemember(false)  // 항상 기억할 것인지의 여부를 결정
			.tokenValiditySeconds(3600*24*7)  // 토큰 유지 시간(7일)
			.rememberMeParameter("remember-me")
			.userDetailsService(userDetailsService)
			.authenticationSuccessHandler(authSuccessHandler);
		
		// 접근권한 설정(권한 부여 : Authorization)
		http
			.authorizeHttpRequests()
			.requestMatchers("/user/**").permitAll()  // 모든 권한 부여
			.requestMatchers("/board/**").hasAnyAuthority("USER", "MASTER")  // 특정 권한 부여
			.requestMatchers("/master/**").hasAnyAuthority("MASTER")
			.anyRequest().authenticated();
		
		// 세션 관리
		http
			.sessionManagement()
			.maximumSessions(1)  // -1이면 무제한 세션 허용
			.maxSessionsPreventsLogin(false)  // true : 중복 로그인 방지, false : 중복 로그인 시 이전 로그인 세션 삭제 후 로그인
			.expiredUrl("/user/login");  // 세션이 만료된 경우 이동 할 URL
		
		// 로그아웃 처리
		http
			.logout()
			.logoutUrl("/board/logout")  // spring security의 로그아웃을 가동시키는 사용자 지정 로그아웃 URL
			.logoutSuccessUrl("/user/login")  // 로그아웃이 성공했을 때 리다이렉트하는 URL
			.invalidateHttpSession(true)  // 로그아웃하면 session 삭제
			.deleteCookies("JSESSIONID", "remember-me")  // JSESSIONID(톰켓에서 자동생성 해주는 쿠키), remember-me(자동 로그인) 쿠키 삭제
			.permitAll();
*/		
		// CSRF, CORS 공격 방어 비활성화
		http
			.formLogin((login) -> login.disable())
			.csrf((csrf) -> csrf.disable())
			.cors((cors) -> cors.disable());
		
		System.out.println("스프링 시큐리티 설정 완료 !!");
		
		return http.build();
	}
	
}
