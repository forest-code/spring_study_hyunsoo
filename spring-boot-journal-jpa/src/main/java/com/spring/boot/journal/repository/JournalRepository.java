package com.spring.boot.journal.repository;

import com.spring.boot.journal.domain.Journal;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by hyunsoo0813 on 2017. 8. 8..
 */
public interface JournalRepository extends JpaRepository<Journal, Long> {

	public List<Journal> findByTitleContaining(String word);
	
	public List<Journal> findByCreatedAfter(Date date);
	
	@Query("select j from Journal j where j.title like %?1%")
	public List<Journal> findByCustomQuery(String word);
	
}
