package com.fanfq.sbt;

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

	public static void main(String[] args) {
		SpringApplication.run(SbtApplication.class, args);
	}

	@Override
	public Health health() {
		//在/health接口调用的时候，返回多一个属性："mySpringBootApplication":{"status":"UP","hello":"world"}
		//Health.up().withDetail("hello", "world").build();
		
		return Health.up().build();
	}

}

