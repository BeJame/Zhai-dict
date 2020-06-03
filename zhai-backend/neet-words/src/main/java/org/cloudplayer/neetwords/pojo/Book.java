package org.cloudplayer.neetwords.pojo;


public class Book {
	private String id;

	private int bookId;

	private String name;

	private int totalWords;

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
