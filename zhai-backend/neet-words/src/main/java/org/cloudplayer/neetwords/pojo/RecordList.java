package org.cloudplayer.neetwords.pojo;

import java.util.List;

public class RecordList {
	private String id;
	private List<Record> recordList;
	private String userId;  //用户id(openid)

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
}
