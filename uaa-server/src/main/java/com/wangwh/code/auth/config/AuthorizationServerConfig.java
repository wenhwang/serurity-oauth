package com.wangwh.code.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
	static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailsService;

	@Resource
	AuthorizationCodeServices authorizationCodeServices;

	@Autowired
	ClientDetailsService clientDetailsService;

	@Autowired
	private TokenStore tokenStore;


	//Token endpoint 安全約束
	//defines the security constraints on the token endpoint.
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.allowFormAuthenticationForClients();//允許表單認證
		security.checkTokenAccess("permitAll()");//公开 /oauth/check_token 访问
		security.tokenKeyAccess("permitAll()");//公开token 对称加密公开访问 /oauth/token_key
	}

	//客户端信息
	//配置 ClientDetailsService
	//a configurer that defines the client details service. Client details can be initialized,
	// or you can just refer to an existing store.
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		super.configure(clients);
		clients.inMemory()
					//客户端id
					.withClient("credential")
					//资源ID
					.resourceIds("order")
					//授权类型 authorization_code, password, client_credentials, implicit, or refresh_token
					.authorizedGrantTypes("client_credentials")
					//客户端密钥
					.secret("secret")
					//作用域
					.scopes("order-service")
					.and()
					.withClient("authcode")
					.secret("secret")
					.authorizedGrantTypes("authorization_code","refresh_token")
					.resourceIds("order")
					.scopes("order-service")
					.autoApprove(true)
					.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
					.refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)
					.redirectUris("http://localhost:8080/oauth/login/client");
	}

	//定义认证endpoint、授权endpoints 以及令牌服务
	//defines the authorization and token endpoints and the token services.
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		//密碼模式需要認證管理器
		endpoints.authenticationManager(authenticationManager)
				.authorizationCodeServices(authorizationCodeServices)//授权码生成方式
				.tokenServices(tokenService())//令牌服务
				.userDetailsService(userDetailsService)//设置userDetailsService刷新token时候会用到
				.setClientDetailsService(clientDetailsService);//客户端信息
	}

	//配置令牌服务
	@Bean
	public AuthorizationServerTokenServices tokenService(){
		DefaultTokenServices serverTokenServices = new DefaultTokenServices();
		serverTokenServices.setSupportRefreshToken(true);//支持刷新Token
		serverTokenServices.setTokenStore(tokenStore);
		return serverTokenServices;
	}
}
