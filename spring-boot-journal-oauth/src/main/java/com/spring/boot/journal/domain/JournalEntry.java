package com.spring.boot.journal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spring.boot.journal.utils.JsonDateSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "entry")
@NoArgsConstructor
public class JournalEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String title;

	@Setter
	private Date created;

	@Getter
	@Setter
	private String summary;

	@Transient
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public JournalEntry(String title, String date, String summary) throws ParseException {
		this.title = title;
		this.created = format.parse(date);
		this.summary = summary;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getCreated() {
		return created;
	}

	@JsonIgnore
	public String getCreatedAsShort() {
		return format.format(created);
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
