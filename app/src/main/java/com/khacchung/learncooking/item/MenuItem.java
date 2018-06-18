package com.khacchung.learncooking.item;

/**
 * Created by Administrator on 24/08/2017.
 */

public class MenuItem {
    private int srcImage;
    private String nameMenu;
    private String url;
    private int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(int srcImage) {
        this.srcImage = srcImage;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MenuItem(int srcImage, String nameMenu, String url, int image) {
        this.image = image;
        this.srcImage = srcImage;
        this.nameMenu = nameMenu;
        this.url = url;
    }
}
