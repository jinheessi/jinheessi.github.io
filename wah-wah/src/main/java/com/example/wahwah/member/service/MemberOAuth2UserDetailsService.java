package com.example.wahwah.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.wahwah.member.dto.MemberOAuth2DTO;
import com.example.wahwah.member.entity.MemberEntity;
import com.example.wahwah.member.entity.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service("MemberOAuth2UserDetailsService")
@RequiredArgsConstructor
public class MemberOAuth2UserDetailsService extends DefaultOAuth2UserService{
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder pwdEncoder;
	private final HttpSession session;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		//인증 후에 보내주는 데이터를 가져옴 데이터는 key,value 구조로 되어있음
		String provider = userRequest.getClientRegistration().getRegistrationId();
		String providerId = oAuth2User.getAttribute("sub");
		String email = oAuth2User.getAttribute("email");
		
		System.out.println("***************** provider =" + provider);
		System.out.println("***************** providerId =" + providerId);
		System.out.println("***************** email =" + email);
		
		Map<String, Object> attributes = oAuth2User.getAttributes();
		Map<String, Object> attributesResponse = (Map<String, Object>) attributes.get("response");
		
		if("naver".equals(provider)) { //네이버 로그인
			email = attributesResponse.get("email").toString();
        }
        else if("kakao".equals(provider)){ //카카오 로그인
			String e = (String) ((Map) attributes.get("kakao_account")).get("email");
			email = e.toString();
        }else{ // 구글 로그인
			email = oAuth2User.getAttribute("email");
		}

		MemberEntity memberEntity = saveSocialMember(email);
		
		//회원 role 값을 가져와서 유저 객체에 저장
		List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>(); 
		SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(memberEntity.getRole());
		grantedAuthorities.add(grantedAuthority);
		
		MemberOAuth2DTO memberOAuth2dto = new MemberOAuth2DTO();
		memberOAuth2dto.setAttributes(oAuth2User.getAttributes());
		memberOAuth2dto.setAuthorities(grantedAuthorities);
		memberOAuth2dto.setName(memberEntity.getUsername());	
		
		session.setAttribute("email", email);
		session.setAttribute("username", memberEntity.getUsername());
		session.setAttribute("role", memberEntity.getRole());
		session.setMaxInactiveInterval(3600*24*7);
		
		return memberOAuth2dto;
	}
	
	
	//인증 통과 후에 tbl_member에 존재하는 회원이면 
	//tbl_member에서 회원정보를 읽어서 리턴하고 
	//존재하지 않는 구글 회원이면 임시회원 등록
	private MemberEntity saveSocialMember(String email) {
		
		Optional<MemberEntity> result = memberRepository.findById(email);
		if(result.isPresent()) { //아이디가 email인 회원이 존재하면..
			return result.get();
		}
		
		//아이디가 email인 회원이 존재하지않으면..
		MemberEntity memberEntity = MemberEntity.builder()
									.email(email)
									.username(email)
									.role("USER")
									.build();
		
		memberRepository.save(memberEntity);
		
		return memberEntity;
	}

}




