package com.khacchung.learncooking.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.khacchung.learncooking.menu.FragmentFavorite;
import com.khacchung.learncooking.menu.FragmentHome;


/**
 * Created by Administrator on 29/06/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentHome();
                break;
            case 1:
                fragment = new FragmentFavorite();
                break;
            case 2:
                fragment = new FragmentFavorite();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Trang chủ";
            case 1:
                return "yêu thích";
            case 2:
                return "Tìm Kiếm";
        }
        return super.getPageTitle(position);
    }
}
