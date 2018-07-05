package com.test.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.test.app.aspect.LoggingAspect;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
	// the Aspect itself must also be a Bean
	@Bean
	public LoggingAspect logAspect() {
		return new LoggingAspect();
	}
}
