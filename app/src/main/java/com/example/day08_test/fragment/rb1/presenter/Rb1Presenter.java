package com.example.day08_test.fragment.rb1.presenter;

import com.example.day08_test.api.Api;
import com.example.day08_test.bean.Rb1Bean;
import com.example.day08_test.bean.XbannerBean;
import com.example.day08_test.fragment.Rb1Fragment;
import com.example.day08_test.fragment.rb1.model.IRb1Model;
import com.example.day08_test.fragment.rb1.model.Rb1Model;

public class Rb1Presenter implements IRb1Presenter{
    Rb1Fragment rb1Fragment;
    private final Rb1Model rb1Model;
    public Rb1Presenter(Rb1Fragment rb1Fragment) {
        this.rb1Fragment = rb1Fragment;
        //初始化model
        rb1Model = new Rb1Model();
    }

    @Override
    public void getRb1PresenterImageData() {
        //调用
        rb1Model.getRb1ModelData(Api.XBANNER_IMAGE,XbannerBean.class, new IRb1Model.Rb1ModelInterface() {
            @Override
            public void success(Object data) {
                rb1Fragment.getRb1ViewImageData(data);
            }

            @Override
            public void failed() {

            }
        });
    }

    @Override
    public void getRb1PresenterData() {
        //调用
        rb1Model.getRb1ModelData(Api.RB1,Rb1Bean.class, new IRb1Model.Rb1ModelInterface() {
            @Override
            public void success(Object data) {
                rb1Fragment.getRb1ViewData(data);
            }

            @Override
            public void failed() {

            }
        });
    }
}
