package com.wangwh.code.auth.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SampleUserdetailsService implements UserDetailsService {
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return User.withUsername("wangwh")
					.password("111")
					.authorities("RWX")
					.build();
	}
}
