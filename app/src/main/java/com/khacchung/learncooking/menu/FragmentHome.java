package com.khacchung.learncooking.menu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.khacchung.learncooking.R;
import com.khacchung.learncooking.adapter.AdapterDanhSach;
import com.khacchung.learncooking.adapter.AdapterDanhSach2;
import com.khacchung.learncooking.adapter.AdapterHome;
import com.khacchung.learncooking.item.ItemDanhSach;
import com.khacchung.learncooking.model.XMLAsync;

import java.util.ArrayList;

/**
 * Created by Administrator on 24/08/2017.
 */

public class FragmentHome extends Fragment {
    private ArrayList<ItemDanhSach> danhSachNgonHomNay = new ArrayList<>();
    private ArrayList<ItemDanhSach> danhSachNgonHomNay1 = new ArrayList<>();
    private AdapterDanhSach adapterNgonHomNay;
    private AdapterDanhSach2 adapterDanhSach2;
    private com.khacchung.learncooking.customer.NonScrollGridview gvNgonHomNay;
    private com.khacchung.learncooking.customer.NonScrollGridview gvXemNhieu;
    private com.github.ksoichiro.android.observablescrollview.ObservableScrollView scroll;
    private ProgressDialog dialog;
    private Main2Activity main2Activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        XMLAsync xmlAsync = new XMLAsync(handler);
        xmlAsync.execute("https://khacchung.000webhostapp.com/RSS/ansang.rss");
        dialog = new ProgressDialog(getActivity(), R.style.ProgressDialog);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Đang tải dữ liệu...");
        main2Activity = (Main2Activity) getActivity();
        boolean checkIntenet = main2Activity.isOnline();
        if (!dialog.isShowing() && checkIntenet)
            dialog.show();
        adapterNgonHomNay = new AdapterDanhSach(getActivity(), danhSachNgonHomNay);
        adapterDanhSach2 = new AdapterDanhSach2(getActivity(), danhSachNgonHomNay1);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == XMLAsync.WHAT_NEWS) {
                danhSachNgonHomNay.addAll((ArrayList<ItemDanhSach>) msg.obj);
                danhSachNgonHomNay1.addAll((ArrayList<ItemDanhSach>) msg.obj);
                adapterNgonHomNay.notifyDataSetChanged();
                adapterDanhSach2.notifyDataSetChanged();
                if (dialog.isShowing())
                    dialog.cancel();
            }

        }
    };

    @Override
    public void onStart() {
        super.onStart();
        gvNgonHomNay = getActivity().findViewById(R.id.gvNgonHomNay);
        gvXemNhieu = getActivity().findViewById(R.id.gvXemNhieu);
        gvNgonHomNay.setAdapter(adapterNgonHomNay);
        gvXemNhieu.setAdapter(adapterDanhSach2);
        scroll = getActivity().findViewById(R.id.scroll);
        gvNgonHomNay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
