package com.example.day08_test.register.model;

import com.example.day08_test.network.Okhttp;

public class RegisterModel implements IRegisterModel {
    RegisterModelInterface registerModelInterface;

    public RegisterModel(RegisterModelInterface registerModelInterface) {
        this.registerModelInterface = registerModelInterface;
    }

    @Override
    public void getRegisterData(String url, String acc, String pwd) {
        //OKHTTP
        Okhttp.okHttpPost(url, acc, pwd, new Okhttp.GetPostListener() {
            @Override
            public void getMess1(String s) {
                registerModelInterface.registerSuccess(s);
            }
        });
    }

    //接口
    public interface RegisterModelInterface{
        void registerSuccess(String datea);
        void registerFailed();
    }
}
