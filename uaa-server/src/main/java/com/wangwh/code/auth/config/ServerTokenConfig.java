package com.wangwh.code.auth.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class ServerTokenConfig {

	//用户
	@Bean
	public UserDetailsService userDetailsService(){
		return new SampleUserdetailsService();
	}

	//密码
	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}

	//令牌持久化
	@Bean
    public TokenStore tokenStore(){
		return new InMemoryTokenStore();
	}

	//授权码生成方式
	@Bean
	public AuthorizationCodeServices authorizationCodeServices(){
		return new InMemoryAuthorizationCodeServices();
	}
}
