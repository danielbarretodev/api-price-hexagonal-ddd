package com.ecommerce.prices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication(scanBasePackages = "com.ecommerce.prices")
@OpenAPIDefinition
public class PricesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricesApplication.class, args);
	}

}
