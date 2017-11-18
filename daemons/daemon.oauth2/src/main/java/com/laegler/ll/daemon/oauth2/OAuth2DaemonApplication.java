package com.laegler.lao.daemon.oauth2;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@SpringBootApplication
@EnableResourceServer
@Controller
@SessionAttributes("authorizationRequest")
public class OAuth2DaemonApplication {

	private static final Logger LOG = LoggerFactory.getLogger(OAuth2DaemonApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(OAuth2DaemonApplication.class, args);
		LOG.info("Started: {}", ctx.getId());
	}

	@ResponseBody
	public Principal user(Principal user) {
		return user;
	}
}
