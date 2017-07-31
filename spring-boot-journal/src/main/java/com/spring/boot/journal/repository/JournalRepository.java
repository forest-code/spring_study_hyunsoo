package com.spring.boot.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.journal.domain.Journal;

public interface JournalRepository extends JpaRepository<Journal, Long> {

}
