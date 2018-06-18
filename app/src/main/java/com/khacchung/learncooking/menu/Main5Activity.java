package com.khacchung.learncooking.menu;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.khacchung.learncooking.R;
import com.khacchung.learncooking.adapter.AdapterChat;
import com.khacchung.learncooking.item.ItemChat;
import com.khacchung.learncooking.model.ActivityBase;
import com.khacchung.learncooking.model.Config;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main5Activity extends ActivityBase implements View.OnClickListener, Runnable {
    private ListView lvChat;
    private EditText edtChat;
    private Button btnChat;
    private String user;
    private SharedPreferences sharedPreferences;
    private ArrayList<ItemChat> arrChat = new ArrayList<>();
    private LinearLayout lnChat;
    private com.khacchung.learncooking.adapter.AdapterChat adapterChat;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chat);
        dialog = ProgressDialog.show(this, "", "Đang tải nội dung...", true);
        dialog.show();
      /*  ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Thread thread = new Thread(this);
        thread.start();
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        user = sharedPreferences.getString("username", "");
        adapterChat = new AdapterChat(this, arrChat);
        initView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        user = sharedPreferences.getString("username", "");
    }

    private void initView() {
        lvChat = (ListView) findViewById(R.id.lvChat);
        lvChat.setAdapter(adapterChat);
        edtChat = (EditText) findViewById(R.id.edtChat);
        btnChat = (Button) findViewById(R.id.btnChat);
        lnChat = (LinearLayout) findViewById(R.id.lnChat);
        if (user != "") {
            lnChat.setVisibility(View.VISIBLE);
        } else {
            lnChat.setVisibility(View.INVISIBLE);
        }
        btnChat.setOnClickListener(this);
    }

    @Override
    protected void doSuccess(String result) {
        // Log.e("CHAT", result);
        try {
            JSONArray jsonArray = new JSONArray(result);
            arrChat.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String date = jsonObject.getString("date");
                String content = jsonObject.getString("content");
                String user = jsonObject.getString("user");
                int id = jsonObject.getInt("id");
                //    Log.e("USER", user + "Content : " + content);
                ItemChat chatItem = new ItemChat(id, user, content, date);
                arrChat.add(chatItem);
            }
            adapterChat.notifyDataSetChanged();
            if (dialog.isShowing()) {
                dialog.cancel();
            }
            //adapterChat.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doError() {
        Snackbar.make(edtChat, "Lỗi, vui lòng thử lại", Snackbar.LENGTH_SHORT).show();
        if (dialog.isShowing()) {
            dialog.cancel();
        }
    }

    @Override
    public void onClick(View view) {
        String content = edtChat.getText().toString().trim();
        edtChat.setText("");
        sendChat(user, content);
        getChat();
    }

    private void sendChat(String user, String content) {
        String api = Config.chat(user, content);
        handlerServer(api);
    }

    private void getChat() {
        String api = Config.getChat();
        handlerServer(api);
    }

    @Override
    public void run() {
        while (true) {
            try {
                getChat();
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
