package edu.udem.prqrdrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PrqrdrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrqrdrsApplication.class, args);
	}

}
