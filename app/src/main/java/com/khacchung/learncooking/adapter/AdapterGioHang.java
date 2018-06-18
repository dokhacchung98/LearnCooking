package com.khacchung.learncooking.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.khacchung.learncooking.R;
import com.khacchung.learncooking.item.ItemGioHang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 09/09/2017.
 */

public class AdapterGioHang extends ArrayAdapter<ItemGioHang> {
    private LayoutInflater inflater;
    private ArrayList<ItemGioHang> itemGioHangs;

    public AdapterGioHang(@NonNull Context context, @NonNull ArrayList<ItemGioHang> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        itemGioHangs = objects;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewGioHang viewGioHang;
        if (convertView == null) {
            viewGioHang = new ViewGioHang();
            convertView = inflater.inflate(R.layout.item_gio_hang, parent, false);
            viewGioHang.txtMonHang = convertView.findViewById(R.id.txtMonHang);
            viewGioHang.txtSoLuong = convertView.findViewById(R.id.txtSoLuong);
            convertView.setTag(viewGioHang);
        } else
            viewGioHang = (ViewGioHang) convertView.getTag();
        ItemGioHang itemGioHang = itemGioHangs.get(position);
        viewGioHang.txtMonHang.setText(itemGioHang.getMonHang());
        viewGioHang.txtSoLuong.setText(itemGioHang.getSoLuong());
        return convertView;
    }

    private class ViewGioHang {
        private TextView txtMonHang;
        private TextView txtSoLuong;
    }
}
