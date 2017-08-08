package com.spring.boot.journal.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hyunsoo0813 on 2017. 8. 8..
 */
@NoArgsConstructor
public class JournalEntry {

    @Getter
    @Setter
    private String title;

    @Getter
    private Date created;

    @Getter
    @Setter
    private String summary;

    private final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    public JournalEntry(String title, String date, String summary) throws ParseException{
        this.title = title;
        this.created = format.parse(date);
        this.summary = summary;
    }

    public void setCreated(String date) throws ParseException {
        Long _date = null;
        try {
            _date = Long.parseLong(date);
            this.created = new Date(_date);
            return;
        } catch (Exception e) {

        }
        this.created = format.parse(date);
    }

    public String toString() {
        StringBuilder value = new StringBuilder("* JournalEntry(");
        value.append("제목: ");
        value.append(title);
        value.append(", 요약: ");
        value.append(summary);
        value.append(", 작성일자: ");
        value.append(format.format(created));
        value.append(")");
        return value.toString();
    }
}
