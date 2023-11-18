package br.com.senac.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.senac.api")
public class ApiTesteSolidApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTesteSolidApplication.class, args);
	}

}
