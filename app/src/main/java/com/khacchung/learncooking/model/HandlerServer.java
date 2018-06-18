package com.khacchung.learncooking.model;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class HandlerServer extends AsyncTask<String,Void,String> {

    public static final int WHAT_DATA = 1;

    private Handler handler;

    public HandlerServer(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected String doInBackground(String... strings) {
        String link = strings[0];
        try{
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(5000);
            InputStream inputStream = connection.getInputStream();
            StringBuilder builder = new StringBuilder();
            byte[] b = new byte[1024];
            int count = inputStream.read(b);
            while (count != -1){
                builder.append(new String(b,0,count,"utf-8"));
                count = inputStream.read(b);
            }
            inputStream.close();

            return builder.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Message message = new Message();
        message.what = WHAT_DATA;
        message.obj = s;
        handler.sendMessage(message);
    }
}
