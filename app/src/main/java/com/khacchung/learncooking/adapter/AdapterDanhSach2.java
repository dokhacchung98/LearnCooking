package com.khacchung.learncooking.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khacchung.learncooking.R;
import com.khacchung.learncooking.item.ItemDanhSach;

import java.util.ArrayList;

/**
 * Created by Administrator on 24/08/2017.
 */

public class AdapterDanhSach2 extends ArrayAdapter<ItemDanhSach> {
    private ArrayList<ItemDanhSach> danhSachs;
    private LayoutInflater inflater;

    public AdapterDanhSach2(@NonNull Context context, @NonNull ArrayList<ItemDanhSach> danhSachs) {
        super(context, android.R.layout.simple_list_item_1, danhSachs);
        this.danhSachs = danhSachs;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_gridview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgReview = convertView.findViewById(R.id.imgReview);
            viewHolder.txtName = convertView.findViewById(R.id.txtName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ItemDanhSach itemDanhSach = danhSachs.get(position);
        viewHolder.txtName.setText(itemDanhSach.getName());
        Glide.with(getContext())
                .load(itemDanhSach.getImage())
                .placeholder(R.mipmap.ic_loading).override(480, 200).centerCrop()
                .error(R.mipmap.ic_error)
                .into(viewHolder.imgReview);
        return convertView;
    }

    public class ViewHolder {
        private TextView txtName;
        private ImageView imgReview;
    }
}
