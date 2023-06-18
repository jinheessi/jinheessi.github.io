package com.example.wahwah.hospitaluser;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.wahwah.hospitaluser.entity.HospitalUserEntity;
import com.example.wahwah.hospitaluser.entity.repository.HospitalUserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final HospitalUserRepository hospitalUserRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

        super.onAuthenticationSuccess(request, response, authentication);

        HospitalUserEntity hospitalUserEntity = hospitalUserRepository.findById(authentication.getName()).get();
        System.out.println("*************** FormLogin 성공 !!! ***************");

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600 * 24 * 7);
        System.out.println(hospitalUserEntity.getHospitalUserId());
        session.setAttribute("hospitalUserId", hospitalUserEntity.getHospitalUserId());
        System.out.println(hospitalUserEntity.getSupervisor());
        session.setAttribute("supervisor", hospitalUserEntity.getSupervisor());
        System.out.println("*************** FormLogin 성공 !!! ***************");

        setDefaultTargetUrl("/master/reservationView");        
    }
}
