package com.example.wahwah.member.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;


import lombok.Setter;

@Setter
public class MemberOAuth2DTO implements OAuth2User{

	Map<String, Object> attributes; //구글에서 인증 후에 넘겨주는 값들
	Collection<? extends GrantedAuthority> authorities; //role
	String name; //아이디
	
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getName() {
		return name;
	}
	
	
}
