package com.spring.boot.journal.web;

import com.spring.boot.journal.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class JournalController {

	private static final String VIEW_INDEX = "index";
	private static final String VIEW_LOGIN = "login";

	@Autowired
	private JournalRepository journalRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mv) {
		mv.setViewName(VIEW_INDEX);
		mv.addObject("journal", journalRepository.findAll());
		return mv;
	}

}
