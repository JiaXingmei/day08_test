package com.example.day08_test.fragment.rb3.presenter;

import com.example.day08_test.api.Api;
import com.example.day08_test.fragment.Rb3Fragment;
import com.example.day08_test.fragment.rb3.model.IRb3Model;
import com.example.day08_test.fragment.rb3.model.Rb3Model;

import java.util.Map;

public class Rb3Presenter implements IRb3Presenter {
    Rb3Fragment rb3Fragment;
    private final Rb3Model rb3Model;

    public Rb3Presenter(Rb3Fragment rb3Fragment) {
        this.rb3Fragment = rb3Fragment;
        rb3Model = new Rb3Model();
    }

    @Override
    public void getPresenterData(Map map) {
        rb3Model.getModelData(Api.RB3, map, new IRb3Model.Rb3ModelInterface() {
            @Override
            public void success(Object data) {
                rb3Fragment.getViewData(data);
            }

            @Override
            public void failed(Exception e) {

            }
        });
    }
}
