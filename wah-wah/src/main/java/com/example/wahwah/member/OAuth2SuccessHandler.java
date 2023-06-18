package com.example.wahwah.member;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	//private final UserService service;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
			HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		//MemberVO userInfo = service.login(authentication.getName());
		System.out.println("*********** 구글 인증 통과 *********");
		
		//세션 생성
		//HttpSession session = request.getSession();
		//session.setMaxInactiveInterval(3600*24*7);
		//session.setAttribute("userid", userInfo.getUserid());
		//session.setAttribute("username", userInfo.getUsername());
		//session.setAttribute("role", userInfo.getRole());	
		
		System.out.println("로그인 성공");
		
		setDefaultTargetUrl("/kids/pediatric");
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
