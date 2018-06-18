package com.khacchung.learncooking.user;

/**
 * Created by Administrator on 28/08/2017.
 */

public class ThanhVien {
    private String user;
    private String pass;
    private String name;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String avatar) {
        this.name = avatar;
    }

    public ThanhVien( String user, String pass, String name) {
        this.user = user;
        this.pass = pass;
        this.name = name;
    }
}
