package com.example.day08_test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day08_test.R;
import com.example.day08_test.bean.Rb1Bean;

public class MyAdapter_Recycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
     Context context;
    Rb1Bean rb1Bean;
    private final int ONE=0;
    private final int TWO=1;
    private final int THREE=2;
    private OnItemClickListener onItemClickListener;

    public MyAdapter_Recycler(Context context, Rb1Bean rb1Bean) {
        this.context = context;
        this.rb1Bean = rb1Bean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i==ONE){
            View view = LayoutInflater.from(context).inflate(R.layout.rb1_recycler01,viewGroup,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }else if(i==TWO){
            View view = LayoutInflater.from(context).inflate(R.layout.rb1_recycler02,viewGroup,false);
            return new ViewHolder1(view);
        }
        return new ViewHolder2(LayoutInflater.from(context).inflate(R.layout.rb1_recycler03,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder){
            LinearLayoutManager layoutManagers = new LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false);
            ((ViewHolder) viewHolder).recyclerView.setLayoutManager(layoutManagers);
            Rb1Adapter01 myAdapter = new Rb1Adapter01(context, rb1Bean);
            ((ViewHolder) viewHolder).recyclerView.setAdapter(myAdapter);
            /*myAdapter.onItemClickCall( new Rb1Adapter01.OnItemClickListener() {
                @Override
                public void onItemClick(String commodityId) {
                    onItemClickListener.onItemClickListener(commodityId);
                }
            });*/
        }else if(viewHolder instanceof ViewHolder1){
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, OrientationHelper.VERTICAL, false);
            ((ViewHolder1) viewHolder).recyclerView1.setLayoutManager(layoutManager);
            Rb1Adapter02 myAdapterTwo = new Rb1Adapter02(context, rb1Bean);
            ((ViewHolder1) viewHolder).recyclerView1.setAdapter(myAdapterTwo);
           /* myAdapterTwo.onItemClickCall(new Rb1Adapter02.OnItemClickListener() {
                @Override
                public void onItemClick(String commodityId) {
                    onItemClickListener.onItemClickListener(commodityId);
                }
            });*/
        }else if(viewHolder instanceof ViewHolder2){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
            ((ViewHolder2) viewHolder).recyclerView2.setLayoutManager(gridLayoutManager);
            Rb1Adapter03 myAdapterThree = new Rb1Adapter03(context, rb1Bean);
            ((ViewHolder2) viewHolder).recyclerView2.setAdapter(myAdapterThree);
            /*myAdapterThree.onItemClickCall(new Rb1Adapter03.OnItemClickListener() {
                @Override
                public void onItemClick(String commodityId) {
                    onItemClickListener.onItemClickListener(commodityId);
                }
            });*/
        }
    }


    @Override
    public int getItemViewType(int position) {
        if(position==ONE){
            return ONE;
        }else if(position==TWO){
            return TWO;
        }
        return THREE;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
    class ViewHolder extends RecyclerView.ViewHolder{

         RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler);
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView1;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            recyclerView1 = itemView.findViewById(R.id.recycler1);
        }
    }
    class ViewHolder2 extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView2;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            recyclerView2 = itemView.findViewById(R.id.recycler2);
        }
    }

    public interface OnItemClickListener{
        public void onItemClickListener(String commodityId);
    }
    public void onItemClick(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
