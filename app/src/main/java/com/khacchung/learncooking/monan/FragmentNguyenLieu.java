package com.khacchung.learncooking.monan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.khacchung.learncooking.R;

/**
 * Created by Administrator on 08/09/2017.
 */

public class FragmentNguyenLieu extends Fragment {
    private WebView webView;
    private String url;
    private QuanLyMonAn quanLyMonAn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        quanLyMonAn = (QuanLyMonAn) getActivity();
        url = quanLyMonAn.getUrlNguyenLieu();
        return inflater.inflate(R.layout.fragment_nguyen_lieu, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        url = quanLyMonAn.getUrlCongThuc();
        Log.e("URRL", url);
        webView = getActivity().findViewById(R.id.wvCongThuc);
        webView.getSettings().setAppCacheMaxSize(5 * 1024 * 1024); // 5MB
        webView.getSettings().setAppCachePath(getActivity().getApplicationContext().getCacheDir().getAbsolutePath());
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT); // load online by default

        /*if (!quanLyMonAn.isNetworkAvailable()) { // loading offline
            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


            String path =
                    Environment.getExternalStorageDirectory().toString() + "/docbao";
            File folder = new File(path);
            File file = new File(folder, "baitap" + uri + ".html");
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
            } catch (IOException e) {
            }
            webView.loadData(text.toString(), "text/html", null);
            return;
        }*/

        webView.loadUrl(url);
    }
}
