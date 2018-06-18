package com.khacchung.learncooking.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.khacchung.learncooking.R;
import com.khacchung.learncooking.adapter.AdapterUser;

import java.util.ArrayList;

/**
 * Created by Administrator on 25/08/2017.
 */

public class Fragment_User extends Fragment implements AdapterView.OnItemClickListener {
    private ArrayList<ChucNang> chucNangs = new ArrayList<>();
    private AdapterUser adapterUser;
    private ListView listView;
    private Main3Activity main3Activity;
    private Button button;
    private de.hdodenhof.circleimageview.CircleImageView imgAvatar;
    private TextView txtName;
    private TextView txtUser;
    public int isLogin = 0;
    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        chucNangs.add(new ChucNang(R.drawable.myds, "Tạo Công Thức", "Tạo Công Thức"));
        chucNangs.add(new ChucNang(R.drawable.dsds, "Danh Sách Công Thức Của Tôi", "Danh Sách Công Thức Của Tôi"));
        chucNangs.add(new ChucNang(R.drawable.ds, "Tạo Thực Đơn Ngày", "Tạo Thực Đơn Ngày"));
        chucNangs.add(new ChucNang(R.drawable.shoping, "Giỏ Đồ Của Tôi", "Giỏ Đồ Của Tôi"));
        chucNangs.add(new ChucNang(R.drawable.clock, "Bộ Báo Giờ", "Bộ Báo Giờ"));
        sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        main3Activity = (Main3Activity) getActivity();
        imgAvatar = getActivity().findViewById(R.id.imgAvatar);
        txtName = getActivity().findViewById(R.id.txtName);
        txtUser = getActivity().findViewById(R.id.txtUser);
        listView = getActivity().findViewById(R.id.lvUser);

        String user = sharedPreferences.getString("username", "");
        String name = sharedPreferences.getString("name", "");
        if (user != "") {
            txtUser.setText(user);
            txtName.setText(name);
        } else {
            txtName.setText("Khách");
            txtUser.setText("Tài khoản khách");
        }

        button = getActivity().findViewById(R.id.btnSetting);
        adapterUser = new AdapterUser(getActivity(), chucNangs);
        listView.setAdapter(adapterUser);
        listView.setOnItemClickListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = popupMenu = new PopupMenu(getActivity(), button);

                if (isLogin == 1) {
                    popupMenu.getMenuInflater().inflate(R.menu.menu_user, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.doiavatar:
                                    break;
                                case R.id.doimatkhau:
                                    break;
                                case R.id.dangxuat:
                                    break;
                            }
                            return true;
                        }
                    });
                } else if (isLogin == 0) {
                    popupMenu.getMenuInflater().inflate(R.menu.menulogin, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.dangky:
                                    main3Activity.switchFragment(main3Activity.getFragmentRegister());
                                    break;
                                case R.id.dangnhap:
                                    main3Activity.switchFragment(main3Activity.getFragmentLogin());
                                    break;
                            }
                            return true;
                        }
                    });
                }
                popupMenu.show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                main3Activity.switchFragment(main3Activity.getFragmentTaoCongThuc());
                break;
            case 1:
                main3Activity.switchFragment(main3Activity.getFragmentDanhSachCuaToi());
                break;
            case 2:
                main3Activity.switchFragment(main3Activity.getFragmentThucDon());
                break;
            case 3:
                main3Activity.switchFragment(main3Activity.getFragmentShopping());
                break;
            case 4:
                main3Activity.switchFragment(main3Activity.getFragmentBamGio());
                break;
        }
    }
}
