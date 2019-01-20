package com.example.day08_test.fragment.rb3.model;

import com.example.day08_test.bean.Rb3Bean;
import com.example.day08_test.network.Okhttp;

import java.util.Map;

public class Rb3Model implements IRb3Model {

    @Override
    public void getModelData(String url, Map map, final Rb3ModelInterface rb3ModelInterface) {
        Okhttp.getInstance().doPost(url,  Rb3Bean.class, map,new Okhttp.NetCallBack() {
            @Override
            public void onSuccess(Object oj) {
                rb3ModelInterface.success(oj);
            }

            @Override
            public void onFailure(Exception e) {
                rb3ModelInterface.failed(e);
            }
        });
    }
}
