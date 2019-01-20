package com.example.day08_test.fragment.rb2.presenter;

import com.example.day08_test.api.Api;
import com.example.day08_test.fragment.Rb2Fragment;
import com.example.day08_test.fragment.rb2.model.IRb2Model;
import com.example.day08_test.fragment.rb2.model.Rb2Model;

public class Rb2Presenter implements IRb2Presenter {
    Rb2Fragment rb2Fragment;
    private final Rb2Model rb2Model;

    public Rb2Presenter(Rb2Fragment rb2Fragment) {
        this.rb2Fragment = rb2Fragment;
        rb2Model = new Rb2Model();
    }

    @Override
    public void getRb2PresenterData() {
        rb2Model.getRb2ModelData(Api.RB2, new IRb2Model.Rb2ModelInterface() {
            @Override
            public void success(String data) {
                rb2Fragment.getRb2ViewData(data);
            }
        });
    }
}
