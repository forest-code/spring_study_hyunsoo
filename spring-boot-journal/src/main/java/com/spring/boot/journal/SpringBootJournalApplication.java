package com.spring.boot.journal;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.boot.journal.domain.Journal;
import com.spring.boot.journal.repository.JournalRepository;

@SpringBootApplication
public class SpringBootJournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJournalApplication.class, args);
	}
	
	@Bean
	InitializingBean saveData(JournalRepository repo) {
		return () -> {
			repo.save(Journal.builder()
					.title("스프링 부트 입문")
					.summary("오늘부터 스프링 부트 공부하기 시작했다.")
					.date("07/19/2017")
					.build());
			repo.save(Journal.builder()
					.title("간단한 스프링 부트 프로젝트")
					.summary("스프링 부트 프로젝트를 처음 만들어보았다.")
					.date("07/31/2017")
					.build());
			repo.save(Journal.builder()
					.title("스프링 부트 해부")
					.summary("스프링 부트를 자세히 살펴보았다.")
					.date("01/08/2017")
					.build());
			repo.save(Journal.builder()
					.title("스프링 부트 클라우드")
					.summary("클라우드 파운드리를 응용한 스프링 부트를 공부하여 마이크로서비스 아키텍쳐를 적용해보고 싶다.")
					.date("02/08/2017")
					.build());
		};
	}
}
