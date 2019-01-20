package com.example.day08_test.fragment.rb1.model;

import android.util.Log;

import com.example.day08_test.bean.Rb1Bean;
import com.example.day08_test.network.Okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Rb1Model implements IRb1Model {
    @Override
    public void getRb1ModelData(final String url,Class clazz,  final Rb1ModelInterface rb1ModelInterface) {
        Okhttp.doGet(url, clazz, new Okhttp.GetMessListener() {
            @Override
            public void getMess(Object s) {
                Log.d("Rb1Model", "getMess: "+s.toString());
                rb1ModelInterface.success(s);

            }
        });
    }

}
