package com.example.graduateproj.mainPack.homePack.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.graduateproj.R;
import com.example.graduateproj.mainPack.homePack.model.RecyclerBean;

import java.util.List;

public class DailyItemAdapter extends RecyclerView.Adapter<DailyItemAdapter.ViewHolder> {

    private final List<RecyclerBean.RecyclerItemBean> beanList;
    private final Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_daily_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclerBean.RecyclerItemBean itemBean = beanList.get(position);

        Glide.with(context).load(itemBean.getSaleImage()).apply(RequestOptions.bitmapTransform(new RoundedCorners(20))).into(holder.saleImage);
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

            saleImage = itemView.findViewById(R.id.daily_image);
            userHead = itemView.findViewById(R.id.daily_head);
            electricStar = itemView.findViewById(R.id.daily_star);

            userName = itemView.findViewById(R.id.daily_name);
            saleText = itemView.findViewById(R.id.daily_text);
            salePrice = itemView.findViewById(R.id.daily_price);
            whoWants = itemView.findViewById(R.id.daily_wants);
        }
    }

    public DailyItemAdapter(Context context, List<RecyclerBean.RecyclerItemBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }
}

