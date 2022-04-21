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
import com.example.graduateproj.commonUI.SelectorImageView;
import com.example.graduateproj.commonUtil.RxClickUtil;
import com.example.graduateproj.mainPack.homePack.model.RecyclerBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;

public class ElectricItemAdapter extends RecyclerView.Adapter<ElectricItemAdapter.ViewHolder> {

    private final List<RecyclerBean.RecyclerItemBean> beanList;
    private final Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_electric_item, parent, false);

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

        RxClickUtil.INSTANCE.clickEvent(holder.starIcon, context)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        holder.starIcon.toggle(!holder.starIcon.isChecked());
                    }
                });
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView saleImage, userHead;
        SelectorImageView starIcon;
        TextView userName, saleText, salePrice, whoWants;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            saleImage = itemView.findViewById(R.id.electric_image);
            userHead = itemView.findViewById(R.id.user_head);
            starIcon = itemView.findViewById(R.id.electric_star);

            userName = itemView.findViewById(R.id.user_name);
            saleText = itemView.findViewById(R.id.sale_text);
            salePrice = itemView.findViewById(R.id.sale_price);
            whoWants = itemView.findViewById(R.id.who_wants);
        }
    }

    public ElectricItemAdapter(Context context, List<RecyclerBean.RecyclerItemBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }
}
