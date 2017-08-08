package com.spring.boot.journal.domain;

import lombok.Builder;
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
public class Journal {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private Date created;

    @Getter
    @Setter
    private String summary;

    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    public String getCreatedAsShort() {
        return format.format(created);
    }

    public String toString() {
        StringBuilder value = new StringBuilder("JournalEntry(");

        value.append("Id: ");
        value.append(this.id);
        value.append(", 제목: ");
        value.append(this.title);
        value.append(", 요약: ");
        value.append(this.summary);
        value.append(", 작성일자: ");
        value.append(this.getCreatedAsShort());
        value.append(")");

        return value.toString();
    }

    @Builder
    public Journal(Long id, String title, String summary, Date date) throws ParseException {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.created = date;
    }
}
