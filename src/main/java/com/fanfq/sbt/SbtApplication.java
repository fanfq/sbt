package com.fanfq.sbt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.fanfq.*"})//添加的注解
public class SbtApplication implements HealthIndicator{
	
	private static final Logger logger = LogManager.getLogger(SbtApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SbtApplication.class, args);
		logger.info("info");
		logger.debug("debug");
		logger.warn("warn");
		logger.error("error");
	}

	@Override
	public Health health() {
		//在/health接口调用的时候，返回多一个属性："mySpringBootApplication":{"status":"UP","hello":"world"}
		//Health.up().withDetail("hello", "world").build();
		
		return Health.up().build();
	}

}

