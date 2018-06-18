package com.khacchung.learncooking.menu;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.khacchung.learncooking.R;
import com.khacchung.learncooking.adapter.AdapterMenu;
import com.khacchung.learncooking.adapter.PagerAdapter;
import com.khacchung.learncooking.model.XMLAsync;
import com.khacchung.learncooking.monan.Main4Activity;
import com.khacchung.learncooking.user.Main3Activity;

import java.net.InetAddress;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements ViewPager.OnPageChangeListener, AdapterView.OnItemClickListener, View.OnClickListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private TextView txtNameUser;
    private ArrayList<com.khacchung.learncooking.item.MenuItem> menuItems = new ArrayList<>();
    private AdapterMenu adapterMenu;
    private com.khacchung.learncooking.customer.NonScrollListView lvMenu;
    private LinearLayout llChat, llUser;
    private SharedPreferences sharedPreferences;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View view = Main2Activity.this.getCurrentFocus();
        toolbar = (Toolbar) findViewById(R.id.toolBarGioHang);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        if (!isOnline()) {
            //  Toast.makeText(this, "Kết nối thất bại", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", "");
            editor.putString("password", "");
            editor.putString("name", "");
            editor.commit();
        } else {
            // Toast.makeText(this, "KẾt nối thành công", Toast.LENGTH_SHORT).show();
        }
        iniView();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    private void iniView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        lvMenu = (com.khacchung.learncooking.customer.NonScrollListView) findViewById(R.id.lvMenu);

        llChat = (LinearLayout) findViewById(R.id.llChat);
        llUser = (LinearLayout) findViewById(R.id.llUser);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(this);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_favorite);

        txtNameUser = (TextView) findViewById(R.id.txtNameUser);
        String name = sharedPreferences.getString("name", "");
        if (!name.equals("")) {
            txtNameUser.setText(name);
        } else {
            txtNameUser.setText("Khách");
        }

        menuItems.add(new com.khacchung.learncooking.item.MenuItem(R.drawable.egg, "Ăn Sáng", "https://khacchung.000webhostapp.com/RSS/ansang.rss", R.drawable.ansang));
        menuItems.add(new com.khacchung.learncooking.item.MenuItem(R.drawable.popcorn, "Ăn Vặt", "https://khacchung.000webhostapp.com/RSS/anvat.rss", R.drawable.anvat));
        menuItems.add(new com.khacchung.learncooking.item.MenuItem(R.drawable.cupcake, "Khai Vị", "https://khacchung.000webhostapp.com/RSS/khaivi.rss", R.drawable.khaivi));
        menuItems.add(new com.khacchung.learncooking.item.MenuItem(R.drawable.cabbage, "Món Chay", "https://khacchung.000webhostapp.com/RSS/monchay.rss", R.drawable.monchay));
        menuItems.add(new com.khacchung.learncooking.item.MenuItem(R.drawable.dish, "Món Chính", "https://khacchung.000webhostapp.com/RSS/monchinh.rss", R.drawable.monchinh));
        menuItems.add(new com.khacchung.learncooking.item.MenuItem(R.drawable.quick, "Nhanh- Dễ", "https://khacchung.000webhostapp.com/RSS/nhanhde.rss", R.drawable.nhanhde));
        menuItems.add(new com.khacchung.learncooking.item.MenuItem(R.drawable.cake, "Làm Bánh", "https://khacchung.000webhostapp.com/RSS/lambanh.rss", R.drawable.lambanh));
        menuItems.add(new com.khacchung.learncooking.item.MenuItem(R.drawable.avocado, "Healthy", "https://khacchung.000webhostapp.com/RSS/healthy.rss", R.drawable.healthy));
        menuItems.add(new com.khacchung.learncooking.item.MenuItem(R.drawable.martini, "Thức Uống", "https://khacchung.000webhostapp.com/RSS/thucuong.rss", R.drawable.douong));
        menuItems.add(new com.khacchung.learncooking.item.MenuItem(R.drawable.salad, "Salad", "https://khacchung.000webhostapp.com/RSS/salad.rss", R.drawable.salad1));
        menuItems.add(new com.khacchung.learncooking.item.MenuItem(R.drawable.soup, "Nước Chấm", "https://khacchung.000webhostapp.com/RSS/nuoccham.rss", R.drawable.nuoccham));
        menuItems.add(new com.khacchung.learncooking.item.MenuItem(R.drawable.spati, "Pasta- Spaghetti", "https://khacchung.000webhostapp.com/RSS/sastaspaghetti.rss", R.drawable.pasta));

        adapterMenu = new AdapterMenu(Main2Activity.this, menuItems);
        lvMenu.setAdapter(adapterMenu);
        lvMenu.setOnItemClickListener(this);
        llUser.setOnClickListener(this);
        llChat.setOnClickListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, 0, 0) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        lvMenu.setSelector(R.color.customcolor);
/*
        drawerLayout.closeDrawers();
*/
        notPressItem();
        Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
        intent.putExtra(Main4Activity.IMAGE_RESOURCE, menuItems.get(i).getImage());
        intent.putExtra(Main4Activity.STRING_RESOURCE, menuItems.get(i).getNameMenu());
        intent.putExtra(Main4Activity.URL_RESOURCE, menuItems.get(i).getUrl());
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.llChat:
                intent = new Intent(Main2Activity.this, Main5Activity.class);
                startActivity(intent);
                notPressItem();
                lvMenu.setSelector(android.R.color.white);
                break;
            case R.id.llUser:
                intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
                notPressItem();
                lvMenu.setSelector(android.R.color.white);
                break;
        }
    }

    public void notPressItem() {
        llUser.setBackgroundColor(Color.WHITE);
        llChat.setBackgroundColor(Color.WHITE);
    }

    public boolean isConectionIntenet() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String name = sharedPreferences.getString("name", "");
        if (!name.equals("")) {
            txtNameUser.setText(name);
        } else {
            txtNameUser.setText("Khách");
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
