package com.wangwh.code.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer // add OAuth2AuthenticationProcessingFilter to Spring security Filter Chain
public class OrderResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	public ResourceServerTokenServices resourceServerTokenServices;

	//ResourceServerSecurityConfigurer
	//  1、构造 OAuth2AuthenticationProcessingFilter 实例
	//  设置BearerTokenExtractor,OAuth2AuthenticationManager,TokenServcie
	//

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("order")
				.stateless(true)
				.tokenServices(resourceServerTokenServices);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/order/*").authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
