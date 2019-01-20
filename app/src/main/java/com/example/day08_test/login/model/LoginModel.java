package com.example.day08_test.login.model;

import android.util.Log;

import com.example.day08_test.login.model.ILoginModel;
import com.example.day08_test.network.Okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginModel implements ILoginModel {
    ModelInterface modelInterface;

    //构造器
    public LoginModel(ModelInterface modelInterface) {
        this.modelInterface = modelInterface;
    }

    @Override
    public void getModelData(String url, String name, String pwd) {
        Okhttp.okHttpPost(url, name, pwd, new Okhttp.GetPostListener() {
            @Override
            public void getMess1(String s) {
                modelInterface.success(s);
            }
        });
    }

    //接口回调
    public interface ModelInterface{
        void success(String data);
        void failed();
    }
}
