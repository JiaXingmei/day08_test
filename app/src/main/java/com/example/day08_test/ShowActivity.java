package com.example.day08_test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.example.day08_test.adapter.ShowAdapter;
import com.example.day08_test.fragment.Rb1Fragment;
import com.example.day08_test.fragment.Rb2Fragment;
import com.example.day08_test.fragment.Rb3Fragment;
import com.example.day08_test.fragment.Rb4Fragment;
import com.example.day08_test.fragment.Rb5Fragment;
import com.hjm.bottomtabbar.BottomTabBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity {
   // @BindView(R.id.bottom_tabbar)
   // BottomTabBar bottom_tabbar;
     private ViewPager view_pager;
    private RadioGroup radio_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        /*ButterKnife.bind(this);
        bottom_tabbar.init(getSupportFragmentManager())
                .setFontSize(8)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED,Color.BLACK)
                .setImgSize(50,50)
                .addTabItem("",R.mipmap.common_tab_btn_home_n_xhdpi,Rb1Fragment.class)
                .addTabItem("",R.mipmap.common_tab_btn_circle_n_xhdpi,Rb2Fragment.class)
                .addTabItem("",R.mipmap.commom_tab_btn_shopping_cart_n_xhdpi,Rb3Fragment.class)
                .addTabItem("",R.mipmap.common_tab_btn_list_n_xhdpi,Rb4Fragment.class)
                .addTabItem("",R.mipmap.common_tab_btn_my_n_hdpi,Rb5Fragment.class);*/
       //初始化控件
        view_pager = findViewById(R.id.view_pager);
        radio_group = findViewById(R.id.radio_group);
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(new Rb1Fragment());
        list.add(new Rb2Fragment());
        list.add(new Rb3Fragment());
        list.add(new Rb4Fragment());
        list.add(new Rb5Fragment());
        view_pager.setAdapter(new ShowAdapter(getSupportFragmentManager(), list));
        //联动
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radio_group.check(radio_group.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.button_rb1:
                        view_pager.setCurrentItem(0, false);
                        break;
                    case R.id.button_rb2:
                        view_pager.setCurrentItem(1, false);
                        break;
                    case R.id.button_rb3:
                        view_pager.setCurrentItem(2, false);
                        break;
                    case R.id.button_rb4:
                        view_pager.setCurrentItem(3, false);
                        break;
                    case R.id.button_rb5:
                        view_pager.setCurrentItem(4, false);
                        break;
                }
            }
        });
    }
}
