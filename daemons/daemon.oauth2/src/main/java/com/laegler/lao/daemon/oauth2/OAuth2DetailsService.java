package com.laegler.lao.daemon.oauth2;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

@Component
public class OAuth2DetailsService implements ClientDetailsService, UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(OAuth2DetailsService.class);

	// @Autowired
	// private UserRestClient userRestClient;

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		Preconditions.checkNotNull(clientId);

		BaseClientDetails details = new BaseClientDetails("test", "customers,product-search",
				"guest,user,admin,read,write", "authorization_code,refresh_token,password,implicit,client_credentials",
				"ROLE_CLIENT,ROLE_TRUSTED_CLIENT", "http://platform-oauth2:8081/redirectUrl");

		if (details.getAdditionalInformation() == null) {
			details.setAdditionalInformation(new HashMap<>());
		}
		details.getAdditionalInformation().put("user_id", null);

		// String secret = store.getStoreSecret();
		String secret = "test";
		details.setClientSecret(secret);

		details.getAccessTokenValiditySeconds();

		return details;
	}

	@Override
	public final UserDetails loadUserByUsername(final String username) {
		LOG.trace("loadUserByUsername({})", username);

		Preconditions.checkNotNull(username);
		User user = new User(username, "test", Lists.newArrayList());

		return user;
	}

}