package com.spring.boot.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.journal.domain.JournalEntry;

public interface JournalRepository extends JpaRepository<JournalEntry, Long> {

}
