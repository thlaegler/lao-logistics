package com.laegler.lao.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigApplication.class);

	public static void main(String[] args) {
		LOG.info("Starting config-daemon ...");
		ConfigurableApplicationContext ctx = SpringApplication.run(ConfigApplication.class, args);
		LOG.info("Started config-daemon: " + ctx.getId());
	}
}
