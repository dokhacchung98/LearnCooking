package com.khacchung.learncooking.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.khacchung.learncooking.R;
import com.khacchung.learncooking.adapter.AdapterGioHang;
import com.khacchung.learncooking.item.ItemGioHang;

import java.util.ArrayList;

/**
 * Created by Administrator on 20/08/2017.
 */

public class FragmentTaoGioHang extends Fragment implements View.OnClickListener {
    private Button btnAdd, btnOk;
    private ListView listView;
    private EditText edtMonHang;
    private EditText edtSoLuong;
    private Main3Activity main3Activity;
    private ArrayList<ItemGioHang> itemGioHangs = new ArrayList<>();
    private AdapterGioHang adapterGioHang;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String duLieu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        main3Activity = (Main3Activity) getActivity();
        sharedPreferences = getActivity().getSharedPreferences("GIOHANG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        duLieu = sharedPreferences.getString("CT", "");
        return inflater.inflate(R.layout.layout_shopping, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        btnAdd = getActivity().findViewById(R.id.btnThem);
        btnOk = getActivity().findViewById(R.id.btnXong);
        listView = getActivity().findViewById(R.id.lvGioHang);
        edtMonHang = getActivity().findViewById(R.id.edtNguyenLieu);
        edtSoLuong = getActivity().findViewById(R.id.edtSoLuong);
        btnOk.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        adapterGioHang = new AdapterGioHang(getActivity(), itemGioHangs);
        listView.setAdapter(adapterGioHang);
    }

    @Override
    public void onClick(View view) {
        String monHang = edtMonHang.getText().toString().trim();
        String soLuong = edtSoLuong.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btnThem:
                if (monHang.isEmpty()) {
                    edtMonHang.setError("Nhập nguyên liệu vào");
                    return;
                }
                if (soLuong.isEmpty()) {
                    edtSoLuong.setError("Nhập số lượng vào");
                    return;
                }
                itemGioHangs.add(new ItemGioHang(monHang, soLuong));
                String ct = monHang + "@" + soLuong;
                duLieu += "$$$" + ct;
                edtMonHang.setText("");
                edtSoLuong.setText("");
                break;
            case R.id.btnXong:
                editor.putString("CT", duLieu);
                editor.commit();
                main3Activity.switchFragment(main3Activity.getFragmentShopping());
                break;
        }
    }
}