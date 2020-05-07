package org.cloudplayer.neetwords.pojo;

public class User {
    private int id;
    private String name;
    private String password;
    private String avatar;
    private int amonutPerDay;
    private int bookId;
    private int timesToChangeBackground;
    private int durationKeepAfterRecite;
    private int tipsDuration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getAmonutPerDay() {
        return amonutPerDay;
    }

    public void setAmonutPerDay(int amonutPerDay) {
        this.amonutPerDay = amonutPerDay;
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
}
