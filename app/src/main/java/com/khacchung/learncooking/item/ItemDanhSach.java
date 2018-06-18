package com.khacchung.learncooking.item;

/**
 * Created by Administrator on 24/08/2017.
 */

public class ItemDanhSach {
    private String name;
    private String url;
    private String pubDate;
    private String image;
    private String nguyenLieu;
    private String review;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public ItemDanhSach(String name, String url, String pubDate, String image, String nguyenLieu, String review) {
        this.name = name;
        this.url = url;
        this.pubDate = pubDate;
        this.image = image;
        this.nguyenLieu = nguyenLieu;
        this.review=review;

    }


    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(String nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ItemDanhSach() {
    }
}
