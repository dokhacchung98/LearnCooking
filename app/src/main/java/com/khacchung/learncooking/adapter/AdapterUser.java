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

import com.khacchung.learncooking.R;
import com.khacchung.learncooking.item.ItemDanhSach;
import com.khacchung.learncooking.user.ChucNang;

import java.util.ArrayList;

/**
 * Created by Administrator on 25/08/2017.
 */

public class AdapterUser extends ArrayAdapter<ChucNang> {
    private LayoutInflater inflater;
    private ArrayList<ChucNang> chucNangs;

    public AdapterUser(Context context, ArrayList<ChucNang> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        inflater = LayoutInflater.from(context);
        chucNangs = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolderUser viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_user, parent, false);
            viewHolder = new ViewHolderUser();
            viewHolder.img = convertView.findViewById(R.id.imgReview2);
            viewHolder.txt = convertView.findViewById(R.id.txtName2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderUser) convertView.getTag();
        }
        ChucNang chucNang = chucNangs.get(position);
        viewHolder.txt.setText(chucNang.getTen());
        viewHolder.img.setBackgroundResource(chucNang.getImage());
        return convertView;
    }

    public class ViewHolderUser {
        private ImageView img;
        private TextView txt;
    }
}
