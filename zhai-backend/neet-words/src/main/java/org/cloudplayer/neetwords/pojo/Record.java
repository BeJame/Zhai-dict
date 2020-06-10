package org.cloudplayer.neetwords.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Record {

	private String id;
	private String word;//单词
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date date;//日期
	private int level;//熟练度
//	private String userId;//用户id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
