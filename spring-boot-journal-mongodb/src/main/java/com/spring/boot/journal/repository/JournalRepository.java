package com.spring.boot.journal.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.boot.journal.domain.Journal;

public interface JournalRepository extends MongoRepository<Journal, String> {

	public List<Journal> findByTitleLike(String word);
	
}
