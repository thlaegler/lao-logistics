package com.laegler.lao.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

	private static final Logger LOG = LoggerFactory.getLogger(EurekaApplication.class);

	public static void main(String[] args) {
		LOG.info("Starting eureka-discovery-daemon ...");
		ConfigurableApplicationContext ctx = SpringApplication.run(EurekaApplication.class, args);
		LOG.info("Started eureka-discovery-daemon: " + ctx.getId());
	}
}
