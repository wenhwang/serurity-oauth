package com.wangwh.code.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
public class ResourcreTokenConfig {

	public static final String CHECK_TOKEN_URL = "http://localhost:8080/oauth/check_token";
	public static final String CLIENT_ID = "authcode";
	public static final String CLIENT_SECRET = "secret";


	@Bean
	public ResourceServerTokenServices remoteTokenServices(){
		RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
		remoteTokenServices.setCheckTokenEndpointUrl(CHECK_TOKEN_URL);
		remoteTokenServices.setClientId(CLIENT_ID);
		remoteTokenServices.setClientSecret(CLIENT_SECRET);
		return remoteTokenServices;
	}

}
