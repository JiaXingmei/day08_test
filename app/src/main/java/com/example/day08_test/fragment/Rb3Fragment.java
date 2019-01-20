package com.example.day08_test.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.day08_test.R;
import com.example.day08_test.adapter.Rb3Adapter01;
import com.example.day08_test.bean.Rb3Bean;
import com.example.day08_test.fragment.rb3.presenter.Rb3Presenter;
import com.example.day08_test.fragment.rb3.view.IRb3View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Rb3Fragment extends Fragment implements IRb3View {
    @BindView(R.id.rb3_recycler_view)
    RecyclerView rb3RecyclerView;
    @BindView(R.id.rb3_checkbox_checkall)
    CheckBox rb3CheckboxCheckall;
    @BindView(R.id.rb3_text_clearing)
    TextView rb3TextClearing;
    Unbinder unbinder;
    @BindView(R.id.rb3_text_money)
    TextView rb3TextMoney;
    private Rb3Presenter rb3Presenter;
    private HashMap<Object, Object> hashMap;
    private List<Rb3Bean.DataBean> list = new ArrayList<>();
    private Rb3Adapter01 rb3Adapter01;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rb3, container, false);
        unbinder = ButterKnife.bind(this, view);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rb3RecyclerView.setLayoutManager(linearLayoutManager);
        rb3Presenter = new Rb3Presenter(this);
        hashMap = new HashMap<>();
        hashMap.put("uid", "71");
        rb3Presenter.getPresenterData(hashMap);
        return view;
    }

    @Override
    public void getViewData(Object rb3ViewData) {
        if (rb3ViewData instanceof Rb3Bean) {
            Rb3Bean rb3Bean = (Rb3Bean) rb3ViewData;
            list = rb3Bean.getData();
            rb3Adapter01 = new Rb3Adapter01(getActivity(),list);
        }
        if (list != null) {
            list.remove(0);
            rb3Adapter01.setList(list);

        }
        rb3RecyclerView.setAdapter(this.rb3Adapter01);
        rb3Adapter01.setListener(new Rb3Adapter01.ShopCallBack() {
            @Override
            public void callBack(List<Rb3Bean.DataBean> list) {
                double zj = 0;
                //勾选商品数量,不是商品的购买数量
                int num = 0;
                int totalNum = 0;
                for (int i = 0; i < list.size(); i++) {
                    //获取商家里面的商品
                    List<Rb3Bean.DataBean.ListBean> listAll = list.get(i).getList();
                    for (int j = 0; j < listAll.size(); j++) {
                        totalNum = totalNum + Integer.parseInt(listAll.get(j).getNum());
                        //取选中状态
                        if (listAll.get(j).isIscheck()) {
                            zj = zj + (listAll.get(j).getPrice() * Integer.parseInt(listAll.get(j).getNum()));
                            //再见=zj+(Double.valueOf(listAll.get(j).getPrice()));
                            num += Integer.parseInt(listAll.get(j).getNum());

                        }
                    }
                }
                if (num < totalNum) {
                    //不是全部选中
                    rb3CheckboxCheckall.setChecked(false);
                } else {
                    //全部选中
                    rb3CheckboxCheckall.setChecked(true);
                }
                rb3TextMoney.setText("合计:" + zj);
                rb3TextClearing.setText("去结算(" + num + ")");
            }
        });

    }

    //修改选中状态,获取价格和数量
    private void checkSeller(boolean b) {
        double zj = 0;
        int num = 0;
        for (int j = 0; j < list.size(); j++) {
            Rb3Bean.DataBean dataBean = list.get(j);
            dataBean.setIscheck(b);

            List<Rb3Bean.DataBean.ListBean> listBeans = list.get(j).getList();
            for (int i = 0; i < listBeans.size(); i++) {
                listBeans.get(i).setIscheck(b);
                zj = zj + ((Integer.parseInt(listBeans.get(i).getNum())) * listBeans.get(i).getPrice());
                //再见=zj+(Integer.parseInt(listBeans.get(i).getNum()))*Double.valueOf(listBeans.get(i).getPrice());
                num = num + Integer.parseInt(listBeans.get(i).getNum());

            }
        }

        if (b) {
            rb3TextMoney.setText("合计:" + zj);
            rb3TextClearing.setText("去结算(" + num + ")");
        } else {
            rb3TextMoney.setText("合计:0.0");
            rb3TextClearing.setText("去结算(0)");
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
