package com.khacchung.learncooking.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.khacchung.learncooking.R;
import com.khacchung.learncooking.model.Config;
import com.khacchung.learncooking.model.FragmentBase;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 16/08/2017.
 */

public class FragmentRegister extends FragmentBase implements View.OnClickListener {
    public static final int UPDATE_IMAGE = 4324;
    private EditText edtUser;
    private EditText edtPass;
    private EditText edtName;
    private Button btnRegister;
    private TextView txtLogin;
    private Main3Activity mainActivity;
    private ArrayList<ThanhVien> thanhViens = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private ProgressDialog dialog;
    private ImageView imgAvaRegister;
    private String uriImage = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialog = new ProgressDialog(getActivity(), R.style.ProgressDialog);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Đang tạo tài khoản...");
        sharedPreferences = getActivity().getSharedPreferences("temp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tempuser", "");
        editor.putString("temppass", "");
        editor.commit();
        return inflater.inflate(R.layout.register_layout, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mainActivity = (Main3Activity) getActivity();
        edtUser = getActivity().findViewById(R.id.edtUser2);
        edtPass = getActivity().findViewById(R.id.edtPass2);
        edtName = getActivity().findViewById(R.id.edtName2);
        btnRegister = getActivity().findViewById(R.id.btnRegister2);
        txtLogin = getActivity().findViewById(R.id.txtLogin2);
        imgAvaRegister = getActivity().findViewById(R.id.imgAvaRegister);

        btnRegister.setOnClickListener(this);
        imgAvaRegister.setOnClickListener(this);
        txtLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnRegister2:
                String userName = edtUser.getText().toString().trim();
                String passWord = edtPass.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                if (userName.isEmpty() || passWord.isEmpty() || name.isEmpty()) {
                    if (userName.isEmpty()) {
                        edtUser.setError("Nhập tài khoản");
                    }
                    if (passWord.isEmpty()) {
                        edtPass.setError("Nhập mật khẩu");
                    }
                    if (name.isEmpty()) {
                        edtName.setError("Nhập tên");
                    }
                    if (dialog.isShowing())
                        dialog.cancel();
                    return;
                }
                /**Xử lý đăng ký*/
                if (!dialog.isShowing())
                    dialog.show();
                getUser();
                break;
            case R.id.txtLogin2:
                mainActivity.switchFragment(mainActivity.getFragmentLogin());
                break;
            case R.id.imgAvaRegister:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, UPDATE_IMAGE);
                break;
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void succeccCreat() {
        if (dialog.isShowing())
            dialog.cancel();

        dialog.setMessage("Đang tải avatar...");
        if (!dialog.isShowing())
            dialog.show();
        Bitmap image = ((BitmapDrawable) imgAvaRegister.getDrawable()).getBitmap();
        Log.e("AAAAAA", image.toString());
        new UpAvatar(image, edtUser.getText().toString().trim());


        Toast.makeText(mainActivity, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tempuser", edtUser.getText().toString().trim());
        editor.putString("temppass", edtPass.getText().toString().trim());
        editor.commit();
        mainActivity.getFragmentLogin().setText(edtUser.getText().toString().trim(), edtPass.getText().toString().trim());
        mainActivity.switchFragment(mainActivity.getFragmentLogin());
    }

    @Override
    protected void doSuccess(String result) {
        Log.e("RESULT", result);
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
            String name = edtName.getText().toString().trim();
            for (int i = 0; i < thanhViens.size(); i++) {
                if (thanhViens.get(i).getUser().equals(userName)) {
                    if (dialog.isShowing())
                        dialog.cancel();
                    Snackbar.make(edtUser, "Tài khoản đã tồn tại", Snackbar.LENGTH_SHORT).show();
                    return;
                }
            }
            String api = Config.register(userName, passWord, name);
            handlerServer(api);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doError() {
        if (dialog.isShowing())
            dialog.cancel();
        Snackbar.make(edtUser, "Lỗi, thử lại sau", Snackbar.LENGTH_SHORT).show();
    }

    private void getUser() {
        String api = Config.getUser();
        handlerServer(api);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_IMAGE && resultCode == getActivity().RESULT_OK && data != null) {
            Uri uri = data.getData();
            uriImage = uri.toString();
            imgAvaRegister.setImageURI(uri);
        }
    }

    private class UpAvatar extends AsyncTask<Void, Void, Void> {
        private Bitmap image;
        private String name;

        public UpAvatar(Bitmap image, String name) {
            this.image = image;
            this.name = name;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            String encode = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("image", encode));
            dataToSend.add(new BasicNameValuePair("name", name));

            HttpParams httpParams = getRequestParams();
            HttpClient httpClient = new DefaultHttpClient(httpParams);
            HttpPost httpPost = new HttpPost(Config.SERVER_ADRESS + "upimage.php?");
            Log.e("Adada", httpPost.getURI().toString());
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(dataToSend));
                httpClient.execute(httpPost);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.e("Thanh cong", "Thanh cong");
            if (dialog.isShowing())
                dialog.cancel();
            Snackbar.make(imgAvaRegister, "Tải ảnh thành công", Snackbar.LENGTH_SHORT).show();
        }
    }

    private HttpParams getRequestParams() {
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 1000 * 30);
        HttpConnectionParams.setSoTimeout(httpParams, 1000 * 30);
        return httpParams;
    }
}
