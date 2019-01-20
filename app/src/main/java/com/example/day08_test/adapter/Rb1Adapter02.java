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

public class Rb1Adapter02 extends RecyclerView.Adapter<Rb1Adapter02.ViewHolder> {

    private Context context;
    private Rb1Bean rb1Bean;
    private ViewHolder holder;

    public Rb1Adapter02(Context context, Rb1Bean rb1Bean) {
        this.context = context;
        this.rb1Bean = rb1Bean;
    }

    @NonNull
    @Override
    public Rb1Adapter02.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.rb1_layout02, null);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Rb1Adapter02.ViewHolder viewHolder, int i) {
        viewHolder.price_list.setText("¥"+rb1Bean.getResult().getMlss().get(0).getCommodityList().get(i).getPrice()+"");
        viewHolder.title_list.setText(rb1Bean.getResult().getMlss().get(0).getCommodityList().get(i).getCommodityName());
        Glide.with(context).load(rb1Bean.getResult().getMlss().get(0).getCommodityList().get(i).getMasterPic()).into(viewHolder.view_image_list);
    }

    @Override
    public int getItemCount() {
        return rb1Bean.getResult().getMlss().get(0).getCommodityList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView view_image_list;
        public final TextView price_list;
        public final TextView title_list;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view_image_list = itemView.findViewById(R.id.view_image_list);
            title_list = itemView.findViewById(R.id.title_list);
            price_list = itemView.findViewById(R.id.price_list);
        }
    }
}
