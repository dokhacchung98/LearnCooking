package com.khacchung.learncooking.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.khacchung.learncooking.menu.FragmentFavorite;
import com.khacchung.learncooking.menu.FragmentHome;
import com.khacchung.learncooking.monan.FragmentCongThuc;
import com.khacchung.learncooking.monan.FragmentDanhGia;
import com.khacchung.learncooking.monan.FragmentNguyenLieu;


/**
 * Created by Administrator on 29/06/2017.
 */

public class PagerAdapterChiTiet extends FragmentPagerAdapter {
    public PagerAdapterChiTiet(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentNguyenLieu();
                break;
            case 1:
                fragment = new FragmentCongThuc();
                break;
           /* case 2:
                fragment = new FragmentDanhGia();
                break;*/
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
                return "Nguyên Liệu";
            case 1:
                return "Cong Thức";
           /* case 2:
                return "Đánh Giá";*/
        }
        return super.getPageTitle(position);
    }
}
