package com.khacchung.learncooking.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.khacchung.learncooking.R;
import com.khacchung.learncooking.item.ItemChat;

import java.util.ArrayList;

/**
 * Created by Administrator on 04/09/2017.
 */

public class AdapterChat extends ArrayAdapter<ItemChat> {
    private ArrayList<ItemChat> itemChats;
    private LayoutInflater inflater;
    private SharedPreferences sharedPreferences;
    private String userMe;

    public AdapterChat(Context context, ArrayList<ItemChat> itemChats) {
        super(context, android.R.layout.simple_list_item_1, itemChats);
        this.itemChats = itemChats;
        sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        userMe = sharedPreferences.getString("username", "");
        //    Log.e("USSERME", userMe);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ItemChat itemChat = itemChats.get(position);
        ViewHolder viewHolder;
        String user = itemChat.getUser();
        //  Log.e("USER", user);
        String content = itemChat.getContent();
        String time = itemChat.getPubTime();
/*
        if (convertView == null) {
*/
        viewHolder = new ViewHolder();
        if (user.equals(userMe)) {
            // Log.e("DDax  trung", "trung");
            convertView = inflater.inflate(R.layout.item_chat2, parent, false);
            viewHolder.txtContent = convertView.findViewById(R.id.txtChat1);
            viewHolder.txtTime = convertView.findViewById(R.id.txtChatTime1);

             /*   viewHolder.txtContent.setText(content);
                viewHolder.txtTime.setText(time);*/
        } else {
            convertView = inflater.inflate(R.layout.item_chat1, parent, false);
            viewHolder.txtTime = convertView.findViewById(R.id.txtChatTime1);
            viewHolder.txtContent = convertView.findViewById(R.id.txtChat1);
            viewHolder.txtName = convertView.findViewById(R.id.txtNameChat);
            viewHolder.imgImage = convertView.findViewById(R.id.imgImage1);
        }
        convertView.setTag(viewHolder);
/*
        } else {
*/
/*
            viewHolder = (ViewHolder) convertView.getTag();
*/
/*
        }
*/
        if (!user.equals(userMe)) {
            viewHolder.txtName.setText(user);
        }
        viewHolder.txtTime.setText(time);
        viewHolder.txtContent.setText(content);
        return convertView;
    }

    public class ViewHolder {
        private de.hdodenhof.circleimageview.CircleImageView imgImage;
        private TextView txtContent;
        private TextView txtName;
        private TextView txtTime;
    }
}
