package com.laegler.lao.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
public class ZuulApplication {

	private static final Logger LOG = LoggerFactory.getLogger(ZuulApplication.class);

	public static void main(String[] args) {
		LOG.info("Starting zuul-gateway-daemon ...");
		ConfigurableApplicationContext ctx = SpringApplication.run(ZuulApplication.class, args);
		LOG.info("Started zuul-gateway-daemon: " + ctx.getId());
	}
}
