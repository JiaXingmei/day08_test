package com.example.day08_test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.day08_test.R;
import com.example.day08_test.adapter.MyAdapter_Recycler;
import com.example.day08_test.adapter.Rb1Adapter01;
import com.example.day08_test.adapter.Rb1Adapter02;
import com.example.day08_test.adapter.Rb1Adapter03;
import com.example.day08_test.bean.Rb1Bean;
import com.example.day08_test.bean.XbannerBean;
import com.example.day08_test.fragment.rb1.presenter.Rb1Presenter;
import com.example.day08_test.fragment.rb1.view.IRb1View;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class Rb1Fragment extends Fragment implements IRb1View {

    private RecyclerView recyclers_view;
    private XBanner x_banner;
    private Rb1Presenter rb1Presenter;
    private Rb1Bean rb1Bean;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rb1, container, false);
        //初始化控件
        x_banner = view.findViewById(R.id.x_banner);
        recyclers_view = view.findViewById(R.id.recyclers_view);
        //初始化presenter
        rb1Presenter = new Rb1Presenter(this);
        rb1Presenter.getRb1PresenterData();
        rb1Presenter.getRb1PresenterImageData();
        return view;
    }

    // 轮播图
    @Override
    public void getRb1ViewImageData(final Object rb1ViewData) {
        Log.i("aaa", "getRb1ViewImageData: "+rb1ViewData);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                XbannerBean xbannerBean = (XbannerBean) rb1ViewData;
                List<XbannerBean.ResultBean> result = xbannerBean.getResult();
                final List<String> list = new ArrayList<>();
                for (int i = 0; i<result.size();i++){
                    Log.i("aaa", "run: "+result.get(i).getImageUrl());
                    list.add(result.get(i).getImageUrl());
                }
                Log.i("aaa", "run: "+list.size());
                x_banner.setData(list,null);
                x_banner.setmAdapter(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, View view, int position) {
                        Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
                    }
                });
                x_banner.setPageTransformer(Transformer.Default);
                x_banner.setPageChangeDuration(1000);
            }
        });
    }

    //数据
    @Override
    public void getRb1ViewData(final Object rb1ViewData) {

                /*GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                recycler_view1.setLayoutManager(gridLayoutManager);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recycler_view2.setLayoutManager(linearLayoutManager);
                GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 2);
                recycler_view1.setLayoutManager(gridLayoutManager1);
                recycler_view1.setAdapter(new Rb1Adapter01(getActivity(),rb1Bean));
                recycler_view2.setAdapter(new Rb1Adapter02(getActivity(),rb1Bean));
                recycler_view3.setAdapter(new Rb1Adapter03(getActivity(),rb1Bean));*/
                Rb1Bean rb1Bean = (Rb1Bean) rb1ViewData;
                Log.i("aaa", "getRb1ViewData: "+rb1Bean.getResult().getMlss().get(0).getName());
                recyclers_view.setLayoutManager(new LinearLayoutManager(getActivity(),OrientationHelper.VERTICAL,false));
                MyAdapter_Recycler myAdapter_recycler = new MyAdapter_Recycler(getActivity(), rb1Bean);
                recyclers_view.setAdapter(myAdapter_recycler);
                /*myAdapter_recycler.onItemClick(new MyAdapter_Recycler.OnItemClickListener() {
                    @Override
                    public void onItemClickListener(String commodityId) {
                        Intent intent = new Intent(getActivity(), XiangActivity.class);
                        intent.putExtra("commodityId",commodityId);
                        startActivity(intent);
                    }
                });*/
    }
}
