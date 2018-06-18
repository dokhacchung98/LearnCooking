package com.khacchung.learncooking.item;

/**
 * Created by Administrator on 09/09/2017.
 */

public class ItemGioHang {
    private String monHang;
    private String soLuong;

    public String getMonHang() {
        return monHang;
    }

    public void setMonHang(String monHang) {
        this.monHang = monHang;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public ItemGioHang(String monHang, String soLuong) {
        this.monHang = monHang;

        this.soLuong = soLuong;
    }
}
