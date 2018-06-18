package com.khacchung.learncooking.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 06/09/2017.
 */

public class Upload {
    public String upload(String sourceFileUri, String link) {
        String result = "";
        String fileName = sourceFileUri;
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            URL url = new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true); // Allow Inputs
            conn.setDoOutput(true); // Allow Outputs
            conn.setUseCaches(false); // Don't use a Cached Copy
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("uploaded_file", fileName);
            dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=uploaded_file;filename="
                    + fileName + lineEnd);
            dos.writeBytes(lineEnd);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            int serverResponseCode = conn.getResponseCode();
            if (serverResponseCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
                BufferedReader buff = new BufferedReader(in);
                String line;
                StringBuffer text = new StringBuffer();
                while ((line = buff.readLine()) != null) {
                    text.append(line);
                }
                result = text.toString();
            }
            fileInputStream.close();
            dos.flush();
            dos.close();
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }
}

