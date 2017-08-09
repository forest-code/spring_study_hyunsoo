package com.spring.boot.journal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.boot.journal.domain.Journal;
import com.spring.boot.journal.repository.JournalRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SimpleMongoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleMongoAppApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(JournalRepository repo) {
		return args -> {
			log.info("> 기존 데이터 삭제...");
			repo.deleteAll();
			
			log.info("> 데이터를 새로 생성...");
			repo.save(Journal.builder()
                    .title("스프링 부트 입문")
                    .summary("오늘부터 스프링 부트를 배웠다.")
                    .date("01/01/2016")
                    .build());
	        repo.save(Journal.builder()
	                .title("간단한 스프링 부트 프로젝트")
	                .summary("스프링 부트 프로젝트를 처음 만들어보았다.")
	                .date("01/02/2016")
	                .build());
	        repo.save(Journal.builder()
	                .title("스프링 부트 해부")
	                .summary("스프링 부트를 자세히 살펴보았다.")
	                .date("02/01/2016")
	                .build());
	        repo.save(Journal.builder()
	                .title("스프링 부트 클라우드")
	                .summary("클라우드 파운드리를 응용한 스프링 부트를 공부했다.")
	                .date("03/01/2016")
	                .build());
	        
	        log.info("> 전체 데이터 조회...");
	        repo.findAll().forEach(entry -> log.info(entry.toString()));
	        
	        log.info("> LIKE로 데이터 조회...");
	        repo.findByTitleLike("클라우드").forEach(entry -> log.info(entry.toString()));
	        
		};
	}
}
