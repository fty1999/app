package com.bawei.com.dssy.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.com.dssy.R;
import com.bawei.com.dssy.model.bean.ShopBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 樊腾亚
 * @Date: 2019/4/18 15:22:14
 * @Description:
 */
public class ShopThreeAdapter extends RecyclerView.Adapter<ShopThreeAdapter.MyViewHolder> {

    private Context context;
    private List<ShopBean.ResultBean.PzshBean.CommodityListBeanX> mList = new ArrayList<>();

    public ShopThreeAdapter(Context context) {
        this.context = context;
    }
    public void setDatae(List<ShopBean.ResultBean.PzshBean.CommodityListBeanX> list){
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item3,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
       myViewHolder.text1.setText(mList.get(i).getCommodityName());
       Glide.with(context).load(mList.get(i).getMasterPic()).into(myViewHolder.imageView1);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView1;
        private final TextView text1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.imageView3);
            text1 = itemView.findViewById(R.id.text3);
        }
    }
}
