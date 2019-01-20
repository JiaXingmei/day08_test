package com.example.day08_test.network;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static okhttp3.internal.Internal.instance;

public class Okhttp {

    private static OkHttpClient okHttpClient;
    private static Request request;
    private static volatile Okhttp instance;
    private static Handler handler = new Handler();
    private static OkHttpClient client;

    //创建拦截器
    private Interceptor getAppInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                Log.e("++++++", "拦截前");
                Response response = chain.proceed(request);
                Log.e("++++++", "拦截后");
                return response;
            }
        };
        return interceptor;
    }

    //添加拦截器
    private Okhttp() {
        File file = new File(Environment.getExternalStorageDirectory(), "cache1");
        client = new OkHttpClient().newBuilder()
                .readTimeout(3000, TimeUnit.SECONDS)
                .connectTimeout(3000, TimeUnit.SECONDS)
                .addInterceptor(getAppInterceptor())
                .cache(new Cache(file, 10 * 1024))
                .build();
    }

    //创建单例
    public static Okhttp getInstance() {
        if (instance == null) {
            synchronized (Okhttp.class) {
                if (null == instance) {
                    instance = new Okhttp();
                }
            }
        }
        return instance;
    }

    //封装get方法
    public static void doGet(String url, final Class clazz, final GetMessListener getMessListener) {
        request = new Request.Builder().get().url(url).build();
        //创建call对象
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Gson gson = new Gson();
                final Object o = gson.fromJson(response.body().string(), clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getMessListener.getMess(o);
                    }
                });
            }
        });
    }

    public void doPost(String url, final Class clazz, Map<String, String> parms, final NetCallBack netCallBack) {

        FormBody.Builder body = new FormBody.Builder();
        //key   value
        for (String key : parms.keySet()) {
            //value的值
            body.add(key, parms.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(body.build())
                .build();
        // 3. 创建出一个Call对象
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onFailure(e);
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 注意：response.body()只能用一次
                // Log.i(TAG, "onResponse: "+response.body().string());
                String result = response.body().string();
                Gson gson = new Gson();
                final Object oj = gson.fromJson(result, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        netCallBack.onSuccess(oj);
                    }
                });
            }
        });
    }

    public interface NetCallBack {
        void onSuccess(Object oj);

        void onFailure(Exception e);
    }


    public static void okHttpGet(String url, final GetMessListener getMessListener) {
        okHttpClient = new OkHttpClient();
        request = new Request.Builder().url(url).method("GET", null).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                getMessListener.getMess(response.body().string());
            }
        });
    }

    public static void okHttpPost(String url, String phone, String pwd, final GetPostListener getMessListener) {
        okHttpClient = new OkHttpClient();
        FormBody build = new FormBody.Builder().add("phone", phone).add("pwd", pwd).build();
        request = new Request.Builder().url(url).post(build).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                getMessListener.getMess1(response.body().string());
            }
        });
    }

    public interface GetMessListener {
        void getMess(Object s);
    }

    public interface GetPostListener {
        void getMess1(String s);
    }

}
