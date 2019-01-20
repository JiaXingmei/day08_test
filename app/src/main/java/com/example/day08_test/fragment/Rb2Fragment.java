package com.example.day08_test.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day08_test.R;
import com.example.day08_test.bean.Rb2Bean;
import com.example.day08_test.fragment.rb2.presenter.Rb2Presenter;
import com.example.day08_test.fragment.rb2.view.IRb2View;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Rb2Fragment extends Fragment implements IRb2View {
    @BindView(R.id.rb2_recycler_view)
    RecyclerView rb2RecyclerView;
    Unbinder unbinder;
    private Rb2Presenter rb2Presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rb2, container, false);
        unbinder = ButterKnife.bind(this, view);
        rb2Presenter = new Rb2Presenter(this);
        rb2Presenter.getRb2PresenterData();
        return view;
    }

    @Override
    public void getRb2ViewData(final String viewData) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
