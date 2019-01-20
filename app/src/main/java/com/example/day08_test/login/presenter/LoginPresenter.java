package com.example.day08_test.login.presenter;

import android.util.Log;

import com.example.day08_test.MainActivity;
import com.example.day08_test.api.Api;
import com.example.day08_test.login.model.LoginModel;

public class LoginPresenter implements ILoginPresenter,LoginModel.ModelInterface{
    MainActivity mainActivity;
    private LoginModel loginModel;


    public LoginPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        //初始化model
        loginModel = new LoginModel(this);
    }

    @Override
    public void getPresenterData(String acc,String pwd) {
        //回调model
        loginModel.getModelData(Api.Login,acc,pwd);
    }

    @Override
    public void success(String data) {
        mainActivity.getViewData(data);
    }

    @Override
    public void failed() {

    }
}
