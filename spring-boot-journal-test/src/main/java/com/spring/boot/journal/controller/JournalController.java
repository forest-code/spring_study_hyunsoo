package com.spring.boot.journal.controller;

import com.spring.boot.journal.domain.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hyunsoo0813 on 2017. 8. 8..
 */
@RestController
public class JournalController {

    private static List<JournalEntry> entries = new ArrayList<>();

    static {
        try {
            entries.add(new JournalEntry("스프링 부트 입문", "01/01/2016", "오늘부터 스프링 부트를 배웠다."));
            entries.add(new JournalEntry("간단한 스프링 부트 프로젝트", "01/02/2016", "스프링 부트 프로젝트를 처음 만들어보았다."));
            entries.add(new JournalEntry("스프링 부트 해부", "02/01/2016", "스프링 부트를 자세히 살펴보았다."));
            entries.add(new JournalEntry("스프링 부트 Cloud", "03/01/2016", "클라우드 파운드리를 응용한 스프링 부트를 공부했다."));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/journal/all")
    public List<JournalEntry> getAll() throws ParseException {
        return entries;
    }

    @RequestMapping("/journal/findBy/title/{title}")
    public List<JournalEntry> findByTitleContains(@PathVariable String title) throws ParseException {
        return entries.stream().filter(entry -> entry.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
    }

    @RequestMapping(value = "/journal", method = RequestMethod.POST)
    public JournalEntry add(@RequestBody JournalEntry entry) {
        entries.add(entry);
        return entry;
    }

}

/*
curl -X POST \
  http://localhost:8080/journal \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 482b8d11-459a-848a-da92-09b6dc83f5fb' \
  -d '{
	"title": "스프링 부트 테스트",
	"created": "06/18/2016",
	"summary": "스프링 부트 단위 테스트를 생성했다."
}'
 */
