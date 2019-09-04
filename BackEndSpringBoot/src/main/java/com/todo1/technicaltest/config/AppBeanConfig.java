package com.todo1.technicaltest.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.todo1.technicaltest.mappers.orika.DummyFieldMapper;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@EnableConfigurationProperties
@ComponentScan(
		value = {
				"com.todo1.technicaltest.config.swagger",				
				"com.todo1.technicaltest.repository",
				"com.todo1.technicaltest.service",
				"com.todo1.technicaltest.mappers.orika",
				"com.todo1.technicaltest.controller"

		})
public class AppBeanConfig {

	/**
	 * Mapper Orika
	 * @return
	 */
	@Bean(name = "dummyFieldMapper")
	public DummyFieldMapper getDummyFieldMapper() {
		DummyFieldMapper dummyFieldMapper = new DummyFieldMapper();
		return dummyFieldMapper;
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
