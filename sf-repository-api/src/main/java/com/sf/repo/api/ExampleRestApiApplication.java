package com.sf.repo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleRestApiApplication {

    private static final Logger log = LoggerFactory.getLogger(ExampleRestApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ExampleRestApiApplication.class, args);
	}
	
}
