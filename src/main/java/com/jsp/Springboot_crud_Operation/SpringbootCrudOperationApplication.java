package com.jsp.Springboot_crud_Operation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.jsp.Springboot_crud_Operation")
public class SpringbootCrudOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudOperationApplication.class, args);
	}

}
