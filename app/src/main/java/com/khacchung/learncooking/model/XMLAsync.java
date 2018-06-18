package com.khacchung.learncooking.model;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.khacchung.learncooking.item.ItemDanhSach;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Admin on 20/07/2017.
 */

public class XMLAsync extends AsyncTask<String, Void, ArrayList<ItemDanhSach>> {

    public static final int WHAT_NEWS = 3121;
    private Handler handler;

    public XMLAsync(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected ArrayList<ItemDanhSach> doInBackground(String... params) {
        ArrayList<ItemDanhSach> itemDanhSaches = new ArrayList<>();
        String link = params[0];
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLHanlder xmlHanlder = new XMLHanlder();
            parser.parse(link, xmlHanlder);
            itemDanhSaches = xmlHanlder.getArrNews();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemDanhSaches;
    }

    @Override
    protected void onPostExecute(ArrayList<ItemDanhSach> itemDanhSaches) {
        super.onPostExecute(itemDanhSaches);
        Message message = new Message();
        message.what = WHAT_NEWS;
        message.obj = itemDanhSaches;
        handler.sendMessage(message);
    }
}
