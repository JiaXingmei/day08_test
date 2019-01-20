package com.example.day08_test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day08_test.R;
import com.example.day08_test.bean.Rb1Bean;

import java.text.BreakIterator;

public class Rb1Adapter01 extends RecyclerView.Adapter {
    private Context context;
    private Rb1Bean rb1Bean;
    private ViewHolder holder;

    public Rb1Adapter01(Context context, Rb1Bean rb1Bean) {
        this.context = context;
        this.rb1Bean = rb1Bean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.rb1_layout01, null);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        holder.price.setText("¥"+rb1Bean.getResult().getPzsh().get(0).getCommodityList().get(i).getPrice()+"");
        holder.title.setText(rb1Bean.getResult().getPzsh().get(0).getCommodityList().get(i).getCommodityName());
        Glide.with(context).load(rb1Bean.getResult().getPzsh().get(0).getCommodityList().get(i).getMasterPic()).into(holder.view_image);
    }

    @Override
    public int getItemCount() {
        return rb1Bean.getResult().getPzsh().get(0).getCommodityList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView view_image;
        public final TextView price;
        public final TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view_image = itemView.findViewById(R.id.view_image);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
        }
    }
}
