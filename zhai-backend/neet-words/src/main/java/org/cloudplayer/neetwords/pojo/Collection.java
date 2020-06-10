package org.cloudplayer.neetwords.pojo;

public class Collection {
    private String id; // objectId
    private String userId;
	private String wordsCollection;  //用分号分隔单词

	public Collection() {
	}

	public Collection(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWordsCollection() {
		return wordsCollection;
	}

	public void setWordsCollection(String wordsCollection) {
		this.wordsCollection = wordsCollection;
	}
}
