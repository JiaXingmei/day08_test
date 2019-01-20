package com.example.day08_test.fragment.rb2.model;

import com.example.day08_test.bean.Rb1Bean;
import com.example.day08_test.network.Okhttp;

public class Rb2Model implements IRb2Model {
    @Override
    public void getRb2ModelData(String url, final Rb2ModelInterface rb2ModelInterface) {
        Okhttp.doGet(url, Rb1Bean.class, new Okhttp.GetMessListener() {
            @Override
            public void getMess(Object s) {

            }
        });
    }
}
