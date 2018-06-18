package com.khacchung.learncooking.model;

import android.util.Log;

import com.khacchung.learncooking.item.ItemDanhSach;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Admin on 20/07/2017.
 */

public class XMLHanlder extends DefaultHandler {

    private ArrayList<ItemDanhSach> arrNews = new ArrayList<>();
    private ItemDanhSach itemDanhSach;

    public static final String ITEM = "item";
    public static final String TITLE = "title";
    public static final String DESC = "description";
    public static final String LINK = "link";
    public static final String PUB_DATE = "pubDate";
    public static final String IMAGE = "image";
    public static final String REVIEW = "review";

    private StringBuilder builder;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        builder = new StringBuilder();
        if (qName.equals(ITEM)) {
            itemDanhSach = new ItemDanhSach();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        builder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (itemDanhSach == null) {
            return;
        }

        switch (qName) {
            case ITEM:
            //    Log.e("Handler", "dang add");
                arrNews.add(itemDanhSach);
                break;
            case TITLE:
             //   Log.e("Handler", "title");
                itemDanhSach.setName(builder.toString());
                break;
            case REVIEW:
              //  Log.e("Handler", "des");
                itemDanhSach.setNguyenLieu(builder.toString());
                break;
            case LINK:
              //  Log.e("Handler", "link");
                itemDanhSach.setUrl(builder.toString());
                break;
            case PUB_DATE:
               // Log.e("Handler", "date");
                itemDanhSach.setPubDate(builder.toString());
                break;
            case IMAGE:
               // Log.e("Handler", "image");
                itemDanhSach.setImage(builder.toString());
                break;
            case DESC:
                itemDanhSach.setReview(builder.toString());
                break;
        }
    }

    public ArrayList<ItemDanhSach> getArrNews() {
        return arrNews;
    }
}
