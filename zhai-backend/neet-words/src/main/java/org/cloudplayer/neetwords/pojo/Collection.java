package org.cloudplayer.neetwords.pojo;

public class Collection {
    private String id; // objectId
    private String name;
    private int userId;
    private int status;//初始化为0，删除收藏设置1
	private String wordsCollection;  //用分号分隔单词

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

	public String[] getWordsCollection() {
		return wordsCollection.split(";");
	}

	public void setWordsCollection(String wordsCollection) {
		this.wordsCollection = wordsCollection;
	}
}
