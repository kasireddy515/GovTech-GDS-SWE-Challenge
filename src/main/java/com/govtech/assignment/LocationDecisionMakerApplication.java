package com.govtech.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableAspectJAutoProxy
@OpenAPIDefinition(info = @Info(title = "GovTech Location Decision Maker API", version = "1.0", description = "GovTech Location Decision Maker APIs Information"))
public class LocationDecisionMakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationDecisionMakerApplication.class, args);
	}
}
