package com.khacchung.learncooking.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.khacchung.learncooking.R;
import com.khacchung.learncooking.adapter.AdapterGioHang;
import com.khacchung.learncooking.item.ItemGioHang;

import java.util.ArrayList;

/**
 * Created by Administrator on 20/08/2017.
 */

public class FragmentShopping extends Fragment implements View.OnClickListener, AdapterView.OnItemLongClickListener {
    private android.support.design.widget.FloatingActionButton floatingActionButton;
    private com.khacchung.learncooking.customer.NonScrollListView listView;
    private Main3Activity main3Activity;
    private ArrayList<ItemGioHang> itemGioHangs = new ArrayList<>();
    private AdapterGioHang adapterGioHang;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String duLieu = "";

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        main3Activity = (Main3Activity) getActivity();
        sharedPreferences = getActivity().getSharedPreferences("GIOHANG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        duLieu = sharedPreferences.getString("CT", "");
        return inflater.inflate(R.layout.fragment_gio_hang, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        floatingActionButton = getActivity().findViewById(R.id.fbAddGioDo);
        listView = getActivity().findViewById(R.id.lnGioHang);
        listView.setOnItemLongClickListener(this);
        floatingActionButton.setOnClickListener(this);

        adapterGioHang = new AdapterGioHang(getActivity(), itemGioHangs);
        listView.setAdapter(adapterGioHang);
        addCongThuc(duLieu, itemGioHangs, adapterGioHang);
    }

    @Override
    public void onClick(View view) {
        main3Activity.switchFragment(main3Activity.getFragmentTaoGioHang());
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        itemGioHangs.remove(i);
        adapterGioHang.notifyDataSetChanged();
        return true;
    }

    private void addCongThuc(String dulieu, ArrayList<ItemGioHang> itemGioHangs, AdapterGioHang adapterGioHang) {
        itemGioHangs.clear();
        String[] a1 = dulieu.split("$$$");
        for (int i = 0; i < a1.length; i++) {
            String[] b1 = a1[i].split("@");
            itemGioHangs.add(new ItemGioHang(b1[0], b1[1]));
        }
        adapterGioHang.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        addCongThuc(duLieu, itemGioHangs, adapterGioHang);
    }
}