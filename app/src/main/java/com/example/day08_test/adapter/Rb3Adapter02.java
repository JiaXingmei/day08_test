package com.example.day08_test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day08_test.R;
import com.example.day08_test.bar.AddJianBar;
import com.example.day08_test.bean.Rb3Bean;

import java.util.List;

public class Rb3Adapter02 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<Rb3Bean.DataBean.ListBean> list;
    private ViewHolder holder;
    private ShopCallBack shopCallBack;

    public Rb3Adapter02(Context context, List<Rb3Bean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //View view = View.inflate(context, R.layout.rb3_layout02, null);
        View view = LayoutInflater.from(context).inflate(R.layout.rb3_layout02, viewGroup, false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof ViewHolder) {
            ((ViewHolder) viewHolder).rb3_text_price.setText("$"+list.get(i).getPrice()+"");
            ((ViewHolder) viewHolder).rb3_text_shangpin.setText(list.get(i).getTitle());
            Glide.with(context).load(list.get(i).getImages().split("\\|")[0].replace("https", "http")).into(((ViewHolder) viewHolder).rb3_img);
            //给输入框赋值
            ((ViewHolder) viewHolder).add_view.setData(this, list, i);
            ((ViewHolder) viewHolder).add_view.setOnCallBack(new AddJianBar.CarCallBack() {
                @Override
                public void callBack() {
                    if (shopCallBack != null) {
                        shopCallBack.callBack();
                    }

                }
            });
            //根据记录状态,改变勾选
            ((ViewHolder) viewHolder).rb3_checkbox_shangpin.setChecked(list.get(i).isIscheck());
            //商品跟商家不同,商品的选中改变监听
            ((ViewHolder) viewHolder).rb3_checkbox_shangpin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //先改变自己的状态
                    list.get(i).setIscheck(isChecked);
                    //回调 ,到activity,选中状态被改变
                    if (shopCallBack != null) {
                        shopCallBack.callBack();
                    }
                    //notifyDataSetChanged();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private  CheckBox rb3_checkbox_shangpin;
        private  ImageView rb3_img;
        private  TextView rb3_text_price;
        private  TextView rb3_text_shangpin;
        private  AddJianBar add_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rb3_checkbox_shangpin = itemView.findViewById(R.id.rb3_checkbox_shangpin);
            rb3_img = itemView.findViewById(R.id.rb3_img);
            rb3_text_price = itemView.findViewById(R.id.rb3_text_price);
            rb3_text_shangpin = itemView.findViewById(R.id.rb3_text_shangpin);
            add_view = itemView.findViewById(R.id.add_view);
        }
    }

    //修改子商品的全选和反选
    public void selectorRemoveAll(boolean ischecked) {
        for (Rb3Bean.DataBean.ListBean listBean : list) {
            listBean.setIscheck(ischecked);
        }
        notifyDataSetChanged();
    }

    public void setLisenter(ShopCallBack shopCallBack) {
        this.shopCallBack = shopCallBack;
    }
    //定义接口
    public interface ShopCallBack {
        void callBack();

    }
}
