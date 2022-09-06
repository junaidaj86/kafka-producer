package com.opera.nexer.urlfeeder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.opera.nexer.urlfeeder.dao")
public class UrlfeederApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlfeederApplication.class, args);
	}

}
