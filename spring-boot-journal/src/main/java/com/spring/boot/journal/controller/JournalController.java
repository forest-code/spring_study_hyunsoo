package com.spring.boot.journal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.journal.domain.Journal;
import com.spring.boot.journal.repository.JournalRepository;

@Controller
public class JournalController {

	@Autowired
	JournalRepository repo;
	
	@RequestMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("journal", repo.findAll());
		return "index";
	}
	
	@RequestMapping(value = "/journal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Journal> getJournal() {
		return repo.findAll();
	}
}
