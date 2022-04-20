package com.example.graduateproj.mainPack.homePack.util;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.graduateproj.R;
import com.example.graduateproj.mainPack.homePack.model.ElectricBean;

import java.util.List;

public class ElectricItemAdapter extends RecyclerView.Adapter<ElectricItemAdapter.ViewHolder> {

    private final List<ElectricBean.ElectricItemBean> beanList;
    private final Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_electric_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ElectricBean.ElectricItemBean itemBean = beanList.get(position);

        Glide.with(context).load(itemBean.getSaleImage()).into(holder.saleImage);
        Glide.with(context).load(itemBean.getUserHead()).into(holder.userHead);

        holder.userName.setText(itemBean.getUserName());
        holder.saleText.setText(itemBean.getSaleText());
        holder.salePrice.setText(itemBean.getSalePrice());
        holder.whoWants.setText(itemBean.getWhoWants());
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView saleImage, userHead, electricStar;
        TextView userName, saleText, salePrice, whoWants;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            saleImage = itemView.findViewById(R.id.electric_image);
            userHead = itemView.findViewById(R.id.user_head);
            electricStar = itemView.findViewById(R.id.electric_star);

            userName = itemView.findViewById(R.id.user_name);
            saleText = itemView.findViewById(R.id.sale_text);
            salePrice = itemView.findViewById(R.id.sale_price);
            whoWants = itemView.findViewById(R.id.who_wants);
        }
    }

    public ElectricItemAdapter(Context context, List<ElectricBean.ElectricItemBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }
}
