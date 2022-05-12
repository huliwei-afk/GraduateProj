package com.example.graduateproj.mainPack.donatePack.util;

import android.app.Activity;
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
import com.example.graduateproj.commonUtil.AppNavigator;
import com.example.graduateproj.commonUtil.RxClickUtil;
import com.example.graduateproj.mainPack.donatePack.model.DonateJsonBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;

public class DonateItemAdapter extends RecyclerView.Adapter<DonateItemAdapter.ViewHolder> {

    private final List<DonateJsonBean.DonateItemBean> beanList;
    private final Activity context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donate_recyclerview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonateJsonBean.DonateItemBean itemBean = beanList.get(position);

        if (itemBean.getSaleIcon() == null) {
            Glide.with(context).load(R.drawable.ic_launcher_background).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(holder.saleIcon);
        } else {
            Glide.with(context).load(itemBean.getSaleIcon()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(holder.saleIcon);
        }
        holder.saleName.setText(itemBean.getSaleName());
        Glide.with(context).load(itemBean.getSaleImage()).into(holder.saleImage);
        holder.saleText.setText(itemBean.getSaleText());

        initEvents(holder);
    }

    private void initEvents(@NonNull ViewHolder holder) {
        RxClickUtil.INSTANCE.clickEvent(holder.starIcon, context)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        holder.starIcon.toggle(!holder.starIcon.isChecked());
                    }
                });

        RxClickUtil.INSTANCE.clickEvent(holder.itemView, context)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        AppNavigator.INSTANCE.openItemActivityFromDonate(context, beanList.get(holder.getAdapterPosition()));
                    }
                });
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView saleIcon, saleImage, commentIcon;
        SelectorImageView starIcon;
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

    public DonateItemAdapter(Activity context, List<DonateJsonBean.DonateItemBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

}
