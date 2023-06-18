package com.example.wahwah.hospitaluser.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.wahwah.hospitaluser.dto.HospitalUserDTO;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private final HospitalUserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HospitalUserDTO hospitalUserDTO = HospitalUserDTO.builder().hospitalUserId(username).build();

		HospitalUserDTO userInfo = service.hospitalUserInfo(hospitalUserDTO); //username --> 아이디
		if(userInfo.equals(null)){
			throw new UsernameNotFoundException("아이디가 존재하지 않습니다.");
		}else if(userInfo.getVerified().equals("N")){
            throw new UsernameNotFoundException("허가되지 않은 사용자입니다.");
        }
		
		//SimpleGrantedAuthority : 여러개의 사용자 Roll값을 받아서 권한을 인식하는 역할을 함
		List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("MASTER");
		grantedAuthorities.add(authority);
		
		//User 생성자 인자 --> 아이디, 패스워드, 사용자롤들
		User user = new User(username, userInfo.getPassword(), grantedAuthorities);
        return user;
    }
}
