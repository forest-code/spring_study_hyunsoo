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

    public List<Journal> findAll() {
        return repo.findAll();
    }
}
