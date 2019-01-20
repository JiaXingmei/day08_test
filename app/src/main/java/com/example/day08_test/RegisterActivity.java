package com.example.day08_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day08_test.bean.RegisterBean;
import com.example.day08_test.register.presenter.RegisterPresenter;
import com.example.day08_test.register.view.IRegiesterView;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity implements IRegiesterView {

    private Button register_btn_registe;
    private EditText register_edit_acc;
    private EditText register_edit_yam;
    private EditText register_edit_pwd;
    private TextView register_text_login;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //初始化控件
        register_btn_registe = findViewById(R.id.register_btn_registe);
        register_edit_acc = findViewById(R.id.register_edit_acc);
        register_edit_yam = findViewById(R.id.register_edit_yzm);
        register_edit_pwd = findViewById(R.id.register_edit_pwd);
        register_text_login = findViewById(R.id.register_text_login);
        //初始化presenter
        registerPresenter = new RegisterPresenter(this);
        //已有账号,跳转登录
        register_text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });
        //点击注册,进行非空验证 跳转
        register_btn_registe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = register_edit_acc.getText().toString();
                String pwd = register_edit_pwd.getText().toString();
                if (TextUtils.isEmpty(acc) || TextUtils.isEmpty(pwd)){
                    Toast.makeText(RegisterActivity.this, "账号或用户名不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    registerPresenter.getRegisterPresenterData(acc,pwd);
                }
                //startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    public void getRegisterViewData(final String registerViewData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                RegisterBean registerBean = gson.fromJson(registerViewData, RegisterBean.class);
                String message = registerBean.getMessage();
                if (!message.equals("手机格式错误")&&!message.equals("改手机号已注册,不能重复注册!")){
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                }
            }
        });
    }
}
