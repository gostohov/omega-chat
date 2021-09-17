package ru.omega.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class OmegaChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmegaChatApplication.class, args);
	}

}
