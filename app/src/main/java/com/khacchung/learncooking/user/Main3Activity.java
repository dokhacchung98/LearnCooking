package com.khacchung.learncooking.user;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.khacchung.learncooking.R;
import com.khacchung.learncooking.model.ActivityBase;

public class Main3Activity extends ActivityBase {
    private FragmentShopping fragmentShopping = new FragmentShopping();
    private FragmentTaoCongThuc fragmentTaoCongThuc = new FragmentTaoCongThuc();
    private FragmentDanhSachCuaToi fragmentDanhSachCuaToi = new FragmentDanhSachCuaToi();
    private FragmentBamGio fragmentBamGio = new FragmentBamGio();
    private FragmentThucDon fragmentThucDon = new FragmentThucDon();
    private Fragment_User fragment_user = new Fragment_User();
    private FragmentLogin fragmentLogin = new FragmentLogin();
    private FragmentRegister fragmentRegister = new FragmentRegister();
    private FragmentTaoGioHang fragmentTaoGioHang = new FragmentTaoGioHang();

    public FragmentTaoGioHang getFragmentTaoGioHang() {
        return fragmentTaoGioHang;
    }

    public void setFragmentTaoGioHang(FragmentTaoGioHang fragmentTaoGioHang) {
        this.fragmentTaoGioHang = fragmentTaoGioHang;
    }
    /*
    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }
*/

    public FragmentLogin getFragmentLogin() {
        return fragmentLogin;
    }

    public void setFragmentLogin(FragmentLogin fragmentLogin) {
        this.fragmentLogin = fragmentLogin;
    }

    public FragmentRegister getFragmentRegister() {
        return fragmentRegister;
    }

    public void setFragmentRegister(FragmentRegister fragmentRegister) {
        this.fragmentRegister = fragmentRegister;
    }

    public FragmentBamGio getFragmentBamGio() {
        return fragmentBamGio;
    }

    public void setFragmentBamGio(FragmentBamGio fragmentBamGio) {
        this.fragmentBamGio = fragmentBamGio;
    }

    public FragmentShopping getFragmentShopping() {
        return fragmentShopping;
    }

    public void setFragmentShopping(FragmentShopping fragmentShopping) {
        this.fragmentShopping = fragmentShopping;
    }

    public FragmentTaoCongThuc getFragmentTaoCongThuc() {
        return fragmentTaoCongThuc;
    }

    public void setFragmentTaoCongThuc(FragmentTaoCongThuc fragmentTaoCongThuc) {
        this.fragmentTaoCongThuc = fragmentTaoCongThuc;
    }

    public FragmentDanhSachCuaToi getFragmentDanhSachCuaToi() {
        return fragmentDanhSachCuaToi;
    }

    public void setFragmentDanhSachCuaToi(FragmentDanhSachCuaToi fragmentDanhSachCuaToi) {
        this.fragmentDanhSachCuaToi = fragmentDanhSachCuaToi;
    }

    public FragmentThucDon getFragmentThucDon() {
        return fragmentThucDon;
    }

    public void setFragmentThucDon(FragmentThucDon fragmentThucDon) {
        this.fragmentThucDon = fragmentThucDon;
    }

    public Fragment_User getFragment_user() {
        return fragment_user;
    }

    public void setFragment_user(Fragment_User fragment_user) {
        this.fragment_user = fragment_user;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initFragment();
    }


    @Override
    protected void doSuccess(String result) {

    }

    @Override
    protected void doError() {

    }

    protected void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frPanelUser, fragment_user);
        transaction.add(R.id.frPanelUser, fragmentDanhSachCuaToi);
        transaction.add(R.id.frPanelUser, fragmentShopping);
        transaction.add(R.id.frPanelUser, fragmentTaoCongThuc);
        transaction.add(R.id.frPanelUser, fragmentThucDon);
        transaction.add(R.id.frPanelUser, fragmentBamGio);
        transaction.add(R.id.frPanelUser, fragmentRegister);
        transaction.add(R.id.frPanelUser, fragmentLogin);
        transaction.add(R.id.frPanelUser, fragmentTaoGioHang);
        transaction.commit();
        switchFragment(fragment_user);
    }

    private void HideAllFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.hide(fragment_user);
        transaction.hide(fragmentThucDon);
        transaction.hide(fragmentTaoCongThuc);
        transaction.hide(fragmentDanhSachCuaToi);
        transaction.hide(fragmentShopping);
        transaction.hide(fragmentBamGio);
        transaction.hide(fragmentLogin);
        transaction.hide(fragmentRegister);
        transaction.hide(fragmentTaoGioHang);
        transaction.commit();
    }

    public void switchFragment(Fragment fragment) {
        ActionBar actionBar = getSupportActionBar();
        if (fragment == fragmentRegister || fragment == fragmentLogin || fragment == fragmentShopping)
            actionBar.hide();
        else actionBar.show();
        HideAllFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.show(fragment);
        transaction.commit();
    }
}
