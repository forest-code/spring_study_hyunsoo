package com.spring.boot.journal;

import com.spring.boot.journal.domain.Journal;
import com.spring.boot.journal.service.JournalService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@SpringBootApplication
public class SimpleJdbcAppApplication implements CommandLineRunner{

	@Autowired
	private JournalService journalService;

	public static void main(String[] args) {
		SpringApplication.run(SimpleJdbcAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("@@ 데이터 생성...");
		journalService.insertData();

		log.info("@@ findAll() 호출...");
		List<Journal> journals = journalService.findAll();
		for (Journal journal : journals) {
			log.info(journal.toString());
		}
	}
}
