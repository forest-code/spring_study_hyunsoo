package com.spring.boot.journal.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Journal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	
	@Transient
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
		value.append(", 일자: ");
		value.append(this.getCreatedAsShort());
		value.append(")");
		return value.toString();
	}
	
	Journal() {
		
	}
	
	@Builder
	public Journal(String title, String summary, String date) throws ParseException {
		this.title = title;
		this.summary = summary;
		this.created = format.parse(date);
	}
}
