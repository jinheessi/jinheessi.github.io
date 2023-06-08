package com.example.wahwah.member;
/*package com.example.wahwah.hospitalmember;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.wahwah.hospitalmember.entity.MasterEntity;
import com.example.wahwah.hospitalmember.entity.repository.MasterRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
    private final MasterRepository masterRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authentication) throws IOException, ServletException {
                
            MasterEntity masterEntity = masterRepository.findById(authentication.getName()).get();

            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(3600*24*7);
            session.setAttribute("user_id", masterEntity.getUser_id());
            session.setAttribute("supervisor", masterEntity.getSupervisor());    
            System.out.println("*************** FormLogin 성공 !!! ***************");

            setDefaultTargetUrl("/");
                
            super.onAuthenticationSuccess(request, response, chain, authentication);
    }


    // @Override
	// public void (HttpServletRequest request, 
	// 		HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		
	// 	super.onAuthenticationSuccess(request, response, authentication);
	// }


}
*/