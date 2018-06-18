package com.khacchung.learncooking.item;

/**
 * Created by Administrator on 04/09/2017.
 */

public class ItemChat {
    private int id;
    private String user;
    private String pubTime;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ItemChat(int id, String user, String content, String pubTime) {

        this.id = id;
        this.user = user;
        this.pubTime = pubTime;
        this.content = content;
    }
}
