package com.Jotto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class JottoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JottoApplication.class, args);
	}

}

