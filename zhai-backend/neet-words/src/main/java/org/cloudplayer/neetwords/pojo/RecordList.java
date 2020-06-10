package org.cloudplayer.neetwords.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RecordList {
	private String id;
	private List<Record> recordList;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String userId;  //用户id(openid)

	private int bookId;

	private Double process; //进度, 用bookId-1做下标表示,用double表示进度百分比

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Record> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Double getProcess() {
		return process;
	}

	public void setProcess(Double process) {
		this.process = process;
	}
}
