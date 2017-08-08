package com.spring.boot.journal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.boot.journal.service.JournalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringBootJournalJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJournalJpaApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(JournalService service) {
		return args -> {
			log.info("@@ 데이터 생성...");
			log.info("### 기본 데이터는 data.sql 파일에 의해 초기화 됩니다. ###");
			
			log.info("@@ findAll() 호출");
			service.findAll().forEach(entry -> log.info(entry.toString()));
		};
	}
}
