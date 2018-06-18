package com.khacchung.learncooking.monan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.khacchung.learncooking.R;
import com.khacchung.learncooking.adapter.AdapterDanhSach;
import com.khacchung.learncooking.adapter.AdapterHome;
import com.khacchung.learncooking.adapter.AdapterMenu;
import com.khacchung.learncooking.customer.NonScrollGridview;
import com.khacchung.learncooking.item.ItemDanhSach;
import com.khacchung.learncooking.menu.Main2Activity;
import com.khacchung.learncooking.model.ActivityBase;
import com.khacchung.learncooking.model.XMLAsync;

import java.util.ArrayList;

public class Main4Activity extends ActivityBase implements ObservableScrollViewCallbacks, AdapterView.OnItemClickListener {
    public static final String IMAGE_RESOURCE = "image";
    public static final String URL_RESOURCE = "url";
    public static final String STRING_RESOURCE = "string";
    private ProgressDialog dialog;
    private android.support.design.widget.CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imgToolBar;
    private int resoucreImage;
    private String url_rss = "";
    private String string = "";
    private com.khacchung.learncooking.customer.NonScrollGridview gvMonAn;
    private ArrayList<ItemDanhSach> danhSachNgonHomNay = new ArrayList<>();
    private AdapterDanhSach adapterDs;
    private android.support.v4.widget.NestedScrollView scrollView;
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dialog = new ProgressDialog(this, R.style.ProgressDialog);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Đang tải dữ liệu...");
        if (!dialog.isShowing())
            dialog.show();
        Intent intent = getIntent();
        resoucreImage = intent.getIntExtra(IMAGE_RESOURCE, R.drawable.ansang);
        string = intent.getStringExtra(STRING_RESOURCE);
        url_rss = intent.getStringExtra(URL_RESOURCE);
        Log.e("rss", url_rss);

        XMLAsync xmlAsync = new XMLAsync(handler);
        xmlAsync.execute(url_rss);

        imgToolBar = (ImageView) findViewById(R.id.imgToolBar);
        gvMonAn = (com.khacchung.learncooking.customer.NonScrollGridview) findViewById(R.id.gvMonAn);
        /*scrollView = (NestedScrollView) findViewById(R.id.scrollMonAn);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                setSupportActionBar(toolbar);
                ActionBar ab = getSupportActionBar();
                if (ab == null) {
                    return;
                }
                if (scrollY > oldScrollY) {
                    if (ab.isShowing()) {
                        ab.hide();
                    }
                } else if (scrollY < oldScrollY) {
                    if (!ab.isShowing()) {
                        ab.show();
                    }
                }
            }
        });*/
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolBarLayout);

        imgToolBar.setImageResource(resoucreImage);
        collapsingToolbarLayout.setTitle(string);

     /* danhSachNgonHomNay.add(new ItemDanhSach("Ngon 1", R.drawable.s, "lll"));
        danhSachNgonHomNay.add(new ItemDanhSach("Ngon 2", R.drawable.s, "lll"));
        danhSachNgonHomNay.add(new ItemDanhSach("Ngon 3", R.drawable.s, "lll"));
        danhSachNgonHomNay.add(new ItemDanhSach("Ngon 4", R.drawable.s, "lll"));
        danhSachNgonHomNay.add(new ItemDanhSach("Ngon 5", R.drawable.s, "lll"));
        danhSachNgonHomNay.add(new ItemDanhSach("Ngon 6", R.drawable.s, "lll"));
        danhSachNgonHomNay.add(new ItemDanhSach("Ngon 7", R.drawable.s, "lll"));
        danhSachNgonHomNay.add(new ItemDanhSach("Ngon 8", R.drawable.s, "lll"));
        danhSachNgonHomNay.add(new ItemDanhSach("Ngon 9", R.drawable.s, "lll"));
        danhSachNgonHomNay.add(new ItemDanhSach("Ngon 10", R.drawable.s, "lll"));*/

        adapterDs = new AdapterDanhSach(Main4Activity.this, danhSachNgonHomNay);
        gvMonAn.setAdapter(adapterDs);
        gvMonAn.setOnItemClickListener(this);
    }

    @Override
    protected void doSuccess(String result) {

    }

    @Override
    protected void doError() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        for (int i = danhSachNgonHomNay.size() - 1; i > 0; i--) {
            danhSachNgonHomNay.remove(i);
        }
        finish();
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
/*        if (ab == null) {
            return;
        }
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }*/
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(Main4Activity.this, QuanLyMonAn.class);
        intent.putExtra(QuanLyMonAn.URL_RESOURCE_1, danhSachNgonHomNay.get(i).getUrl());
        intent.putExtra(QuanLyMonAn.URL_RESOURCE_2, danhSachNgonHomNay.get(i).getReview());
        intent.putExtra(QuanLyMonAn.STRING_RESOURCE_1, danhSachNgonHomNay.get(i).getReview());
        startActivity(intent);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == XMLAsync.WHAT_NEWS) {
                danhSachNgonHomNay.addAll((ArrayList<ItemDanhSach>) msg.obj);
                adapterDs.notifyDataSetChanged();
                if (dialog.isShowing())
                    dialog.cancel();
/*                for (ItemDanhSach ite : danhSachNgonHomNay) {
                    Log.e("ARR", ite.getImage());
                }*/
            }
        }
    };
}
