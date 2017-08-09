package com.spring.boot.journal.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Journal {
	
	@Id
	@Getter
	@Setter
	private String id;
	
	@Getter
	@Setter
	private String title;
	
	@Getter
	@Setter
	private Date created;
	
	@Getter
	@Setter
	private String summary;
	
	@Transient
	private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	
	public String getCreatedAsShort() {
		return format.format(created);
	}

	@Builder
	public Journal(String title, String date, String summary) throws ParseException {
		super();
		this.title = title;
		this.created = format.parse(date);
		this.summary = summary;
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

}
