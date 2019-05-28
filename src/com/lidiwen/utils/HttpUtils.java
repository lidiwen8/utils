package com.lidiwen.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    /**
     * 根据url返回响应结果 get请求
     *
     * @param httpurl
     * @return
     */
    public static StringBuffer getRequest(String httpurl) {
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();

        try {
            URL url = new URL(httpurl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int code = con.getResponseCode();
            if (code == 200) {
                InputStream is = con.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String strRead = null;
                while ((strRead = reader.readLine()) != null) {
                    sbf.append(strRead);
                    sbf.append("\r\n");
                }
                reader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbf;
    }

    /**
     * 根据url返回响应结果 post请求
     *
     * @param httpurl
     * @return
     */
    public static StringBuffer postRequest(String httpurl) {
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(httpurl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            OutputStream writer = con.getOutputStream();
            writer.flush();
            writer.close();

            con.connect();

            int code = con.getResponseCode();
            if (code == 200) {
                InputStream is = con.getInputStream();
                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String strRead = null;
                while ((strRead = reader.readLine()) != null) {
                    sbf.append(strRead);
                    sbf.append("\r\n");
                }
                reader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbf;
    }

    /**
     * 根据url返回响应结果 get请求
     *
     * @param httpurl
     * @return
     */
    public static byte[] getRequestToByte(String httpurl) {
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();

        FileOutputStream out = null;
        ByteArrayOutputStream output = null;
        byte[] byteFile = null;

        try {
            URL url = new URL(httpurl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int code = con.getResponseCode();
            if (code == 200) {
                InputStream is = con.getInputStream();
                output = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    output.write(buffer, 0, len);
                }
                byteFile = output.toByteArray();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteFile;
    }
}
