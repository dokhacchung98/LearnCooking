package com.khacchung.learncooking.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.khacchung.learncooking.user.ThanhVien;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 15/07/2017.
 */

public class UtilDadabase {
    public static final String PATH = Environment.getDataDirectory().getPath()
            + "/data/com.khacchung.learncooking/databases/";
    public static final String DB_NAME = "NguoiDung.sql";
    public static final String TABLE_NAME = "NguoiDung";
    public static final String ID = "ID";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String NAME = "NAME";
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public UtilDadabase(Context context) {
        this.context = context;
        copyFileToDevice();
    }

    private void copyFileToDevice() {
        File file = new File(PATH + DB_NAME);
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            parentFile.mkdirs();
            try {
                InputStream inputStream = context.getAssets().open(DB_NAME);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] b = new byte[1024];
                int count = inputStream.read(b);
                while (count != -1) {
                    fileOutputStream.write(b, 0, count);
                    count = inputStream.read(b);
                }
                inputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openedDatabase() {
        sqLiteDatabase = context.openOrCreateDatabase(DB_NAME, context.MODE_PRIVATE, null);
    }

    private void closeDatabase() {
        sqLiteDatabase.close();
    }

    public ArrayList<ThanhVien> getDataThanhVien() {
        ArrayList<ThanhVien> thanhViens = new ArrayList<>();
        openedDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null, null);
        cursor.getCount();
        cursor.moveToFirst();
        //int indexId = cursor.getColumnIndex(ID);
        int indexUser = cursor.getColumnIndex(USERNAME);
        int indexPass = cursor.getColumnIndex(PASSWORD);
        int indexName = cursor.getColumnIndex(NAME);


        while (cursor.isAfterLast() == false) {
            String user = cursor.getString(indexUser);
            String pass = cursor.getString(indexPass);
            String name = cursor.getString(indexName);
            ThanhVien thanhVien = new ThanhVien(user, pass, name);
            thanhViens.add(thanhVien);
            cursor.moveToNext();
        }
        closeDatabase();
        return thanhViens;
    }
}
