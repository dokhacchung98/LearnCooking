package com.khacchung.learncooking.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.khacchung.learncooking.R;
import com.khacchung.learncooking.model.Config;
import com.khacchung.learncooking.model.FragmentBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 16/08/2017.
 */

public class FragmentLogin extends FragmentBase implements View.OnClickListener {
    private EditText edtUser;
    private EditText edtPass;
    private Button btnLogin;
    private TextView txtRegister;
    private Main3Activity mainActivity;
    private ArrayList<ThanhVien> thanhViens = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        dialog = new ProgressDialog(getActivity(), R.style.ProgressDialog);
        dialog.setIndeterminate(false);
        dialog.setMessage("Đang xử lý...");
        return inflater.inflate(R.layout.login_layout, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
      /*  ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.hide();*/
        mainActivity = (Main3Activity) getActivity();
        edtUser = getActivity().findViewById(R.id.edtUser1);
        edtPass = getActivity().findViewById(R.id.edtPass1);
        btnLogin = getActivity().findViewById(R.id.btnLogin1);
        txtRegister = getActivity().findViewById(R.id.txtRegister1);
        btnLogin.setOnClickListener(this);
        txtRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin1:
                String userName = edtUser.getText().toString().trim();
                String passWord = edtPass.getText().toString().trim();
                if (userName.isEmpty() || passWord.isEmpty()) {
                    if (userName.isEmpty()) {
                        edtUser.setError("Nhập tài khoản");
                    }
                    if (passWord.isEmpty()) {
                        edtPass.setError("Nhập mật khẩu");
                    }
                    if (dialog.isShowing())
                        dialog.cancel();
                    return;
                }
                if (!dialog.isShowing())
                    dialog.show();
                getUser();
                break;
            case R.id.txtRegister1:
                mainActivity.switchFragment(mainActivity.getFragmentRegister());
                break;
        }
    }

    private void getUser() {
        String api = Config.getUser();
        handlerServer(api);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void succeccCreat() {

    }

    @Override
    protected void doSuccess(String result) {
       /* Log.e("RESULT", result);
        result = result.substring(result.indexOf("[{\""));
        Log.e("RESULT2", result);*/
        try {
            JSONArray jsonArray = new JSONArray(result);
            thanhViens.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String pass = jsonObject.getString("password");
                String user = jsonObject.getString("username");
                ThanhVien thanhVien = new ThanhVien(user, pass, name);
                thanhViens.add(thanhVien);
            }
            String userName = edtUser.getText().toString().trim();
            String passWord = edtPass.getText().toString().trim();
            for (int i = 0; i < thanhViens.size(); i++) {
                if (thanhViens.get(i).getUser().equals(userName) && thanhViens.get(i).getPass().equals(passWord)) {
                    if (dialog.isShowing())
                        dialog.cancel();
                    Toast.makeText(mainActivity, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", thanhViens.get(i).getUser());
                    editor.putString("password", thanhViens.get(i).getPass());
                    editor.putString("name", thanhViens.get(i).getName());
                    editor.commit();
                    mainActivity.getFragment_user().onStart();
                    mainActivity.switchFragment(mainActivity.getFragment_user());

                    return;
                } else if (thanhViens.get(i).getUser().equals(userName) && !thanhViens.get(i).getPass().equals(passWord)) {
                    if (dialog.isShowing())
                        dialog.cancel();
                    edtPass.setError("Mật khẩu không đúng");

                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doError() {
        Snackbar.make(edtUser, "Lỗi, thử lại sau", Snackbar.LENGTH_SHORT).show();
    }

    public void setText(String user, String pass) {
        edtPass.setText(pass);
        edtUser.setText(user);
    }


}
