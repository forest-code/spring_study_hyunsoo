package com.spring.boot.journal.repository;

import com.spring.boot.journal.domain.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hyunsoo0813 on 2017. 8. 8..
 */
public interface JournalRepository extends JpaRepository<Journal, Long> {

}
