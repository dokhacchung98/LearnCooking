package com.khacchung.learncooking.user;

/**
 * Created by Administrator on 25/08/2017.
 */

public class ChucNang {
    private int image;
    private String ten;
    private String key;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ChucNang(int image, String ten, String key) {

        this.image = image;
        this.ten = ten;
        this.key = key;
    }
}
