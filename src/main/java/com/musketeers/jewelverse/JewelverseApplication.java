package com.musketeers.jewelverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JewelverseApplication {

	public static void main(String[] args) {
		SpringApplication.run(JewelverseApplication.class, args);
	}

}
