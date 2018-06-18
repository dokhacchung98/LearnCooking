package com.khacchung.learncooking.model;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public abstract class ActivityBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void handlerServer(String api) {
        HandlerServer handlerServer = new HandlerServer(handler);
        handlerServer.execute(api);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == HandlerServer.WHAT_DATA) {
                String result = msg.obj.toString();
                if (result.isEmpty()) {
                    doError();
                } else {
                    doSuccess(result);
                }
            }
        }
    };

    protected abstract void doSuccess(String result);


    protected abstract void doError();

}
