package com.spring.boot.journal.service;

import com.spring.boot.journal.domain.Journal;
import com.spring.boot.journal.repository.JournalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by hyunsoo0813 on 2017. 8. 8..
 */
@Slf4j
@Service
public class JournalService {

    @Autowired
    JournalRepository repo;

    public void insertData() throws ParseException {
        log.info("> 데이터 생성...");
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
    }

    public List<Journal> findAll() {
        return repo.findAll();
    }
}
