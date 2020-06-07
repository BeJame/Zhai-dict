package org.cloudplayer.neetwords.pojo;

public class Configuration {
	private String id;
	private String userId;
	private int amountPerDay; //每日单词量
	private int bookId;
	private int timesToChangeBackground;  //多少个词换一次背景图 默认为1
	private int durationKeepAfterRecite;  //单词拼写完后停留时间(ms)
	private int tipsDuration;  //提示弹窗时长 (ms)
	private String imageType;
	private String collectionId;  //收藏的id

	public Configuration() {
	}

	//默认配置
	public Configuration(String userId) {
		this.amountPerDay = 20;
		this.bookId = 1;
		this.timesToChangeBackground = 1;
		this.durationKeepAfterRecite = 1500;
		this.tipsDuration = 1500;
		this.imageType = "二次元";
		this.collectionId = null;
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

	public int getAmountPerDay() {
		return amountPerDay;
	}

	public void setAmountPerDay(int amountPerDay) {
		this.amountPerDay = amountPerDay;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getTimesToChangeBackground() {
		return timesToChangeBackground;
	}

	public void setTimesToChangeBackground(int timesToChangeBackground) {
		this.timesToChangeBackground = timesToChangeBackground;
	}

	public int getDurationKeepAfterRecite() {
		return durationKeepAfterRecite;
	}

	public void setDurationKeepAfterRecite(int durationKeepAfterRecite) {
		this.durationKeepAfterRecite = durationKeepAfterRecite;
	}

	public int getTipsDuration() {
		return tipsDuration;
	}

	public void setTipsDuration(int tipsDuration) {
		this.tipsDuration = tipsDuration;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}
}
