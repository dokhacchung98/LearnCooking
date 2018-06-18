package com.khacchung.learncooking.splashcreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khacchung.learncooking.R;

/**
 * Created by Administrator on 16/08/2017.
 */

public class FragmentSplashScene extends Fragment implements Runnable {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.logo_layout, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
       /* while (true) {
            try {
                Thread.sleep(2000);
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.deleteSplashScene();
                mainActivity.addFragment();
                mainActivity.swithFragment(mainActivity.getFragmentLogin());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}
