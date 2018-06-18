package com.khacchung.learncooking.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 24/08/2017.
 */

public class AdapterDanhSach extends ArrayAdapter<ItemDanhSach> {
    private ArrayList<ItemDanhSach> danhSachs;
    private LayoutInflater inflater;

    public AdapterDanhSach(@NonNull Context context, @NonNull ArrayList<ItemDanhSach> danhSachs) {
        super(context, android.R.layout.simple_list_item_1, danhSachs);
        this.danhSachs = danhSachs;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_danh_sach1, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgReview = convertView.findViewById(R.id.imgReview1);
            viewHolder.txtName = convertView.findViewById(R.id.txtName1);
            viewHolder.txtName2 = convertView.findViewById(R.id.txtName2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ItemDanhSach itemDanhSach = danhSachs.get(position);
        viewHolder.txtName.setText(itemDanhSach.getName());
        viewHolder.txtName2.setText(itemDanhSach.getReview());
        //viewHolder.imgReview.setBackgroundResource(itemDanhSach.getReview());
        Glide.with(getContext())
                .load(itemDanhSach.getImage())
                .placeholder(R.mipmap.ic_loading)
                .error(R.mipmap.ic_error)
                .into(viewHolder.imgReview);
      /*  URL url = null;
        try {
            url = new URL(itemDanhSach.getImage());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            viewHolder.imgReview.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        return convertView;
    }

    public class ViewHolder {
        private TextView txtName;
        private TextView txtName2;
        private ImageView imgReview;
    }
}
