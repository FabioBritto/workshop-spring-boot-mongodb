package com.fabiobritto.workshopmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Workshop Spring Boot e MongoDB", version = "1", description = "API com Spring Boot e Banco de Dados NoSQL MongoDB desenvolvida no curso do Professor Nélio Alves"))
@SpringBootApplication
public class WorkshopmongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkshopmongoApplication.class, args);
	}

}
