package com.todo1.technicaltest.config.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config  implements WebMvcConfigurer
{

	@Bean
	public Docket swaggerPerson()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Person")
				.select()
				.paths(regex("/person/*.*"))
				.apis(RequestHandlerSelectors.basePackage("com.todo1.technicaltest.controller"))
				.build()
				.apiInfo(apiInfo()).pathMapping("/");
	}
	
	@Bean
	public Docket swaggerProduct()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Product")
				.select()
				.paths(regex("/product/*.*"))
				.apis(RequestHandlerSelectors.basePackage("com.todo1.technicaltest.controller"))
				.build()
				.apiInfo(apiInfo()).pathMapping("/");
	}
	
	@Bean
	public Docket swaggerKardexIn()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("KardexIn")
				.select()
				.paths(regex("/kardexin/*.*"))
				.apis(RequestHandlerSelectors.basePackage("com.todo1.technicaltest.controller"))
				.build()
				.apiInfo(apiInfo()).pathMapping("/");
	}

	@Bean
	public Docket swaggerKardexOut()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("KardexOut")
				.select()
				.paths(regex("/kardexout/*.*"))
				.apis(RequestHandlerSelectors.basePackage("com.todo1.technicaltest.controller"))
				.build()
				.apiInfo(apiInfo()).pathMapping("/");
	}

	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder()
				.title("Challenge Todo1")
				.description("Challenge Todo1")
				.license("Apache License Version 2.0")
				.version("1.0")
				.build();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/v1/api-docs", "/v1/api-docs");
		registry.addRedirectViewController("/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
		registry.addRedirectViewController("/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
		registry.addRedirectViewController("/swagger-resources", "/swagger-resources");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}