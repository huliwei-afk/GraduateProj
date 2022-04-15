package com.example.graduateproj.mainPack.donatePack.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.graduateproj.R;
import com.example.graduateproj.mainPack.donatePack.model.DonateJsonBean;

import java.util.List;

public class DonateItemAdapter extends RecyclerView.Adapter<DonateItemAdapter.ViewHolder> {

    private final List<DonateJsonBean.DonateItemBean> beanList;
    private final Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donate_recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonateJsonBean.DonateItemBean itemBean = beanList.get(position);

        Glide.with(context).load(itemBean.getSaleIcon()).into(holder.saleIcon);
        holder.saleName.setText(itemBean.getSaleName());
        Glide.with(context).load(itemBean.getSaleImage()).into(holder.saleImage);
        holder.saleText.setText(itemBean.getSaleText());
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView saleIcon, saleImage, starIcon, commentIcon;
        TextView saleName, saleText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            saleIcon = itemView.findViewById(R.id.sale_icon);
            saleImage = itemView.findViewById(R.id.sale_image);
            starIcon = itemView.findViewById(R.id.donate_fragment_star);
            commentIcon = itemView.findViewById(R.id.donate_fragment_comment);

            saleName = itemView.findViewById(R.id.sale_name);
            saleText = itemView.findViewById(R.id.sale_text);
        }
    }

    public DonateItemAdapter(Context context, List<DonateJsonBean.DonateItemBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

}
