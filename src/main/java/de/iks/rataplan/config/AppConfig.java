package de.iks.rataplan.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource({ "classpath:/application.properties" })
@ComponentScan(basePackages = "de.iks.rataplan")
public class AppConfig {

	@Autowired
	public Environment environment;	
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource beDataSource() {
		return DataSourceBuilder
				.create()
				.username(environment.getProperty("JDBC_DATABASE_USERNAME"))
				.password(environment.getProperty("JDBC_DATABASE_PASSWORD"))
				.driverClassName(environment.getProperty("JDBC_DATABASE_DRIVER"))
				.url(environment.getProperty("JDBC_DATABASE_URL"))
				.build();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource authDataSource() {
		return DataSourceBuilder
				.create()
				.username(environment.getProperty("JDBC_DATABASE_USERNAME_AUTH"))
				.password(environment.getProperty("JDBC_DATABASE_PASSWORD_AUTH"))
				.driverClassName(environment.getProperty("JDBC_DATABASE_DRIVER_AUTH"))
				.url(environment.getProperty("JDBC_DATABASE_URL_AUTH"))
				.build();
	}
}
