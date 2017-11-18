package com.laegler.lao.service.tour;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EntityScan(basePackages = "com.laegler.lao.model")
// @EnableDiscoveryClient
public class TourApplication {

	private static final Logger LOG = LoggerFactory.getLogger(TourApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(TourApplication.class, args);
		LOG.info("Started {}", ctx.getId());
	}

}
