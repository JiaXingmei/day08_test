package com.example.day08_test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day08_test.bean.LoginBean;
import com.example.day08_test.login.presenter.LoginPresenter;
import com.example.day08_test.login.view.ILoginView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements ILoginView {

    private EditText login_edit_acc;
    private EditText login_edit_pwd;
    private CheckBox login_checkbox_remember;
    private TextView login_text_register;
    private Button login_btn_login;
    private LoginPresenter loginPresenter;
    private String acc;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        login_edit_acc = findViewById(R.id.login_edit_acc);
        login_edit_pwd = findViewById(R.id.login_edit_pwd);
        login_checkbox_remember = findViewById(R.id.login_checkbox_remember);
        login_text_register = findViewById(R.id.login_text_register);
        login_btn_login = findViewById(R.id.login_btn_login);
        //初始化presenter
        loginPresenter = new LoginPresenter(this);
        login_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //非空判断---获取输入框内容
                acc = login_edit_acc.getText().toString();
                pwd = login_edit_pwd.getText().toString();
                if (TextUtils.isEmpty(acc) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(MainActivity.this, "账号或用户名不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    loginPresenter.getPresenterData(acc, pwd);
                }
            }
        });
        //记住密码
        SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean flag = sharedPreferences.getBoolean("flag", false);
        login_checkbox_remember.setChecked(flag);
        if (flag) {
            String phone = sharedPreferences.getString("phone", "");
            String pwd1 = sharedPreferences.getString("pwd", "");
            login_edit_acc.setText(phone);
            login_edit_pwd.setText(pwd1);
        }
        //快速注册
        login_text_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }

    @Override
    public void getViewData(final String viewData) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        LoginBean loginBean = gson.fromJson(viewData, LoginBean.class);
                        String message = loginBean.getMessage();
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        if (message.equals("登录成功")) {
                            //记住密码---传值修改
                            SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            if (login_checkbox_remember.isChecked()) {
                                edit.putBoolean("flag", true);
                                edit.putString("phone", acc);
                                edit.putString("pwd", pwd);
                            } else {
                                edit.putBoolean("flag", false);
                            }
                            edit.commit();
                            startActivity(new Intent(MainActivity.this, ShowActivity.class));
                        }
                    }
                });
            }
        }.start();
    }
}
