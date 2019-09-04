package com.todo1.technicaltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.todo1.technicaltest.config.AppBeanConfig;
import com.todo1.technicaltest.config.DatasourceConfig;
import com.todo1.technicaltest.config.swagger.Swagger2Config;

/**
 * 
 * @author Julian Valencia
 * 29/08/2019
 */
@SpringBootApplication
@Import(
	    value = {
	      AppBeanConfig.class,
	      DatasourceConfig.class,
	      Swagger2Config.class
	    })	
public class MainApplication {

	public static void main(final String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	
}
