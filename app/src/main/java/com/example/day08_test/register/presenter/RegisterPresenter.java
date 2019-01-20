package com.example.day08_test.register.presenter;

import com.example.day08_test.RegisterActivity;
import com.example.day08_test.api.Api;
import com.example.day08_test.register.model.RegisterModel;

public class RegisterPresenter implements IRegisterPresenter,RegisterModel.RegisterModelInterface {
    RegisterActivity registerActivity;
    private final RegisterModel registerModel;

    public RegisterPresenter(RegisterActivity registerActivity) {
        this.registerActivity = registerActivity;
        //初始化model
        registerModel = new RegisterModel(this);
    }

    @Override
    public void getRegisterPresenterData(String acc, String pwd) {
        //回调model
        registerModel.getRegisterData(Api.REGISTER,acc,pwd);
    }

    @Override
    public void registerSuccess(String datea) {
        registerActivity.getRegisterViewData(datea);
    }

    @Override
    public void registerFailed() {

    }
}
