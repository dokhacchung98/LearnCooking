package com.khacchung.learncooking.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khacchung.learncooking.R;

/**
 * Created by Administrator on 20/08/2017.
 */

public class FragmentDemGio extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_hen_gio, container, false);
    }
}