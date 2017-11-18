package com.laegler.lao.service.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EntityScan(basePackages = "com.laegler.lao.model")
// @EnableDiscoveryClient
public class PaymentApplication {

	private static final Logger LOG = LoggerFactory.getLogger(PaymentApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(PaymentApplication.class, args);
		LOG.info("Started {}", ctx.getId());
	}

}
