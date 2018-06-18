package com.khacchung.learncooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.khacchung.learncooking.R;
import com.khacchung.learncooking.item.MenuItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 24/08/2017.
 */

public class AdapterMenu extends ArrayAdapter<MenuItem> {
    private LayoutInflater inflater;
    private ArrayList<MenuItem> menuItems;

    public AdapterMenu(Context context, ArrayList<MenuItem> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        inflater = LayoutInflater.from(context);
        menuItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HoldView holdView;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_menu, parent, false);
            holdView = new HoldView();
            holdView.txtMenu = convertView.findViewById(R.id.textmenu);
            holdView.imgMenu = convertView.findViewById(R.id.menuimage);
            convertView.setTag(holdView);
        } else {
            holdView = (HoldView) convertView.getTag();
        }
        MenuItem menuItem = menuItems.get(position);
        holdView.imgMenu.setBackgroundResource(menuItem.getSrcImage());
        holdView.txtMenu.setText(menuItem.getNameMenu());
        return convertView;
    }

    public class HoldView {
        private ImageView imgMenu;
        private TextView txtMenu;
    }
}
