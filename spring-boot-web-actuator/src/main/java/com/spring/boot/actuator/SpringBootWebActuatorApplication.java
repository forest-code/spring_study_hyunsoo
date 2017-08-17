package com.spring.boot.actuator;

import com.spring.boot.actuator.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class SpringBootWebActuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebActuatorApplication.class, args);
	}

	/**
	 * flyway 또는 liquibase를 테스트
	 */
	@Bean
	CommandLineRunner findAll(PersonRepository personRepository) {
		return args -> {
			log.info(">> DB에서 조회한 Person :");
			personRepository.findAll().forEach(person -> log.info(person.toString()));
		};
	}

	/**
	 * 액추에이터 Metric 테스트
	 */
	@Autowired
	private CounterService counter;

	@RequestMapping("/")
	public String index() {
		counter.increment("counter.index.invoked");
		return "스프링 부트 액추에이터!!!";
	}
}
