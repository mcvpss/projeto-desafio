package com.br.back;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.br.back")
@EnableJpaAuditing
@Slf4j
@ComponentScan({"com.br.back", "com.br", "ma.glasnost.orika"})
@EntityScan("com.br.back")
@SpringBootApplication
public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}

	@PostConstruct
	public void init(){
		log.info("------------- INICIOU -------------");
	}
}
