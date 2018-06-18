package com.khacchung.learncooking.model;

import android.net.Uri;

public class Config {

    public static final String SERVER_ADRESS = "https://khacchung.000webhostapp.com/api/";

    public static String getUser() {
        return SERVER_ADRESS + "getuser.php";
    }

    public static String chat(String user, String content) {
        user = Uri.encode(user);
        content = Uri.encode(content);
        return SERVER_ADRESS + "chat.php?user=" + user + "&content=" + content;
    }

    public static String changerUser(String user, String pass, int sex, String name) {
        user = Uri.encode(user);
        pass = Uri.encode(pass);
        name = Uri.encode(name);
        return SERVER_ADRESS + "change.php?username=" + user + "&pass=" + pass + "&sex=" + sex + "&name=" + name;
    }

    public static String register(String user, String pass, String name) {
        user = Uri.encode(user);
        pass = Uri.encode(pass);
        name = Uri.encode(name);
        return SERVER_ADRESS + "register.php?username=" + user + "&pass=" + pass + "&image=chung&name=" + name;
    }

    public static String rate(String name, String user, String content, int star) {
        user = Uri.encode(user);
        content = Uri.encode(content);
        name = Uri.encode(name);
        return SERVER_ADRESS + "rate.php?name=" + user + "&user=" + user + "&content=" + content + "&star=" + star;
    }

    public static String getRate() {
        return SERVER_ADRESS + "getrate.php";
    }

    public static String getChat() {
        return SERVER_ADRESS + "getchat.php";
    }

    public static String deleteChat(int id) {
        return SERVER_ADRESS + "deletechat.php?id=" + id;
    }

}
