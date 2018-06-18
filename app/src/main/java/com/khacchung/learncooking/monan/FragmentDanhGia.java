package com.khacchung.learncooking.monan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khacchung.learncooking.R;

/**
 * Created by Administrator on 08/09/2017.
 */

public class FragmentDanhGia extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nguyen_lieu, container, false);
    }
}
