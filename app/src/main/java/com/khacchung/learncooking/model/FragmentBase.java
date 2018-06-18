package com.khacchung.learncooking.model;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

public abstract class FragmentBase extends Fragment {
    protected abstract void initView();

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
                } else if (result.trim().equals("1")) {
                    succeccCreat();
                } else {
                    doSuccess(result);
                }
            }
        }
    };

    protected abstract void succeccCreat();

    protected abstract void doSuccess(String result);

    protected abstract void doError();
}
