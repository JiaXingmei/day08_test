package com.example.day08_test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.day08_test.R;
import com.example.day08_test.bean.Rb3Bean;

import java.util.ArrayList;
import java.util.List;

public class Rb3Adapter01 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ViewHolder holder;
    private List<Rb3Bean.DataBean> mList;
    public Rb3Adapter01(Context context,List<Rb3Bean.DataBean> mList) {
        this.context = context;
        this.mList=mList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rb3_layout01, viewGroup, false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        ((ViewHolder) viewHolder).rb3_text_shangjia.setText(mList.get(i).getSellerName());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, OrientationHelper.VERTICAL, false);
        ((ViewHolder) viewHolder).rb3_recycler2_shangpin.setLayoutManager(linearLayoutManager);
        List<Rb3Bean.DataBean.ListBean> list = mList.get(i).getList();
        final Rb3Adapter02 rb3Adapter02 = new Rb3Adapter02(context, list);
        ((ViewHolder) viewHolder).rb3_recycler2_shangpin.setAdapter(rb3Adapter02);

        ((ViewHolder) viewHolder).rb3_checkbox_shangjia.setChecked(mList.get(i).isIscheck());

        rb3Adapter02.setLisenter(new Rb3Adapter02.ShopCallBack() {
            @Override
            public void callBack() {
                //从商品适配器回调回来
                if (shopCallBack != null) {
                    shopCallBack.callBack(mList);
                }
                List<Rb3Bean.DataBean.ListBean> listBeans = mList.get(i).getList();

                //创建一个临时的标志位，用来记录现在点击的状态
                boolean isAllChecked = true;
                for (Rb3Bean.DataBean.ListBean bean : listBeans) {
                    if (!bean.isIscheck()) {
                        //只要有一个商品未选中，标志位设置成false，并且跳出循环
                        isAllChecked = false;
                        break;
                    }
                }
                //刷新商家的状态
                ((ViewHolder) viewHolder).rb3_checkbox_shangjia.setChecked(isAllChecked);
                mList.get(i).setIscheck(isAllChecked);
            }
        });
        //监听checkBox的点击事件
        //目的是改变旗下所有商品的选中状态
        ((ViewHolder) viewHolder).rb3_checkbox_shangjia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //首先改变自己的标志位
                mList.get(i).setIscheck(isChecked);
                //调用产品adapter的方法，用来全选和反选
                rb3Adapter02.selectorRemoveAll(isChecked);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public void setList(List<Rb3Bean.DataBean> mlist) {
        this.mList=mlist;
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView rb3_text_shangjia;
        private final CheckBox rb3_checkbox_shangjia;
        private final RecyclerView rb3_recycler2_shangpin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rb3_checkbox_shangjia = itemView.findViewById(R.id.rb3_checkbox_shangjia);
            rb3_text_shangjia = itemView.findViewById(R.id.rb3_text_shangjia);
            rb3_recycler2_shangpin = itemView.findViewById(R.id.rb3_recycler2_shangpin);
        }
    }

    private ShopCallBack shopCallBack;

    public void setListener(ShopCallBack shopCallBack) {
        this.shopCallBack = shopCallBack;
    }

    //定义接口
    public interface ShopCallBack {
        void callBack(List<Rb3Bean.DataBean> list);
    }




}
