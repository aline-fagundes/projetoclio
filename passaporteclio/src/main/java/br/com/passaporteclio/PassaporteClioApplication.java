package br.com.passaporteclio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class PassaporteClioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PassaporteClioApplication.class, args);
	}
}
