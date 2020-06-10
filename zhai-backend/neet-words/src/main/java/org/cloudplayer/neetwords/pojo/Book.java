package org.cloudplayer.neetwords.pojo;


public class Book {
	private String id;

	private int bookId;

	private String name;

	private String description;

	private int totalWords;

	private String image;

	private String cdnAddr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalWords() {
		return totalWords;
	}

	public void setTotalWords(int totalWords) {
		this.totalWords = totalWords;
	}

	public String getCdnAddr() {
		return cdnAddr;
	}

	public void setCdnAddr(String cdnAddr) {
		this.cdnAddr = cdnAddr;
	}
}
