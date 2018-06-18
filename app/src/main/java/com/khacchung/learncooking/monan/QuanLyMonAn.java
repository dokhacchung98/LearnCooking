package com.khacchung.learncooking.monan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.khacchung.learncooking.R;
import com.khacchung.learncooking.adapter.AdapterDanhSach;
import com.khacchung.learncooking.adapter.PagerAdapterChiTiet;
import com.khacchung.learncooking.item.ItemDanhSach;
import com.khacchung.learncooking.model.ActivityBase;

import java.util.ArrayList;

/**
 * Created by Administrator on 08/09/2017.
 */

public class QuanLyMonAn extends ActivityBase implements ViewPager.OnPageChangeListener {
    public static final String IMAGE_RESOURCE_1 = "image";
    public static final String URL_RESOURCE_1 = "url";
    public static final String URL_RESOURCE_2 = "url2";
    public static final String STRING_RESOURCE_1 = "string";
    private ProgressDialog dialog;
    private android.support.design.widget.CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imgToolBar;
    private int resoucreImage;
    private android.support.v4.widget.NestedScrollView scrollView;
    private android.support.v7.widget.Toolbar toolbar;
    private String urlNguyenLieu;
    private String urlCongThuc;
    private ViewPager viewPagerChiTiet;
    private PagerAdapterChiTiet pagerAdapterChiTiet;
    private TabLayout tabLayout;
    private TextView txtTextView;
    private String review = "";

    public String getReview() {
        return review;
    }

    public String getUrlNguyenLieu() {
        return urlNguyenLieu;
    }

    public String getUrlCongThuc() {
        return urlCongThuc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        toolbar = (Toolbar) findViewById(R.id.toolBarChiTiet);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        urlCongThuc = intent.getStringExtra(URL_RESOURCE_1);
        urlNguyenLieu=intent.getStringExtra(URL_RESOURCE_2);
        review = intent.getStringExtra(STRING_RESOURCE_1);
        Log.e("LLLLLLLLLL", urlCongThuc);
        initView();
    }

    private void initView() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolChiTiet);
        imgToolBar = (ImageView) findViewById(R.id.imgToolBaChiTiet);
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutChiTiet);
        viewPagerChiTiet = (ViewPager) findViewById(R.id.viewPagerChiTiet);
        txtTextView = (TextView) findViewById(R.id.txtChiTiet);
        pagerAdapterChiTiet = new PagerAdapterChiTiet(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPagerChiTiet);
        viewPagerChiTiet.setAdapter(pagerAdapterChiTiet);
        viewPagerChiTiet.addOnPageChangeListener(this);
        txtTextView.setText(review);


    }

    @Override
    protected void doSuccess(String result) {

    }

    @Override
    protected void doError() {

    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
}
