package com.todo1.technicaltest.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value= {"classpath:application.properties"})
@EnableJpaRepositories(basePackages = "com.todo1.technicaltest.repository" 
						)
public class DatasourceConfig {

	
	/**
	 * Configuración pruebas unitarias en base de datos embebida
	 * @return
	 */
	@Bean
	@Profile("test")
	public DataSource testDataSource() {

	    return new EmbeddedDatabaseBuilder()
	            .setType(EmbeddedDatabaseType.H2)
	            .build();
	}

}

