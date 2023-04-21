package com.flx.mdc.utils;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.flx.mdc.config.OkHttpMdcInterceptor;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttpUtils {

    private static OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new OkHttpMdcInterceptor()).build();

    public static String doGet(String url) {
        Request request = new Request.Builder().url(url).build();
        final Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return JSONUtil.toJsonStr(response.body());
    }

}
