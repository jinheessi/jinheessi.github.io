package com.example.wahwah.member;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, 
			HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		
		System.out.println("로그인 실패"); 
		//System.out.println(exception.getMessage()); 
		setDefaultFailureUrl("/kids/login");
		
		super.onAuthenticationFailure(request, response, exception);
	}
	
}
