package com.example.graduateproj.mainPack.homePack.util;

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
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.graduateproj.R;
import com.example.graduateproj.commonUI.SelectorImageView;
import com.example.graduateproj.commonUtil.AppNavigator;
import com.example.graduateproj.commonUtil.RxClickUtil;
import com.example.graduateproj.mainPack.homePack.model.RecyclerBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;

public class OtherItemAdapter extends RecyclerView.Adapter<OtherItemAdapter.ViewHolder> {

    private final List<RecyclerBean.RecyclerItemBean> beanList;
    private final Activity context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_other_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecyclerBean.RecyclerItemBean itemBean = beanList.get(position);

        // https://blog.csdn.net/LoveFHM/article/details/120318085 Glide加载圆角失效，与android:adjustViewBounds冲突
        Glide.with(context).load(itemBean.getSaleImage()).apply(new RequestOptions().transform(new FitCenter(), new GranularRoundedCorners(20, 20, 0, 0))).into(holder.saleImage);
        Glide.with(context).load(itemBean.getUserHead()).into(holder.userHead);

        holder.userName.setText(itemBean.getUserName());
        holder.saleText.setText(itemBean.getSaleText());
        holder.salePrice.setText(itemBean.getSalePrice());
        holder.whoWants.setText(itemBean.getWhoWants());

        initEvents(holder);
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    // 保证getAdapterPosition返回的数据正确
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView saleImage, userHead;
        SelectorImageView starIcon;
        TextView userName, saleText, salePrice, whoWants;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            saleImage = itemView.findViewById(R.id.other_image);
            userHead = itemView.findViewById(R.id.other_head);
            starIcon = itemView.findViewById(R.id.other_star);

            userName = itemView.findViewById(R.id.other_name);
            saleText = itemView.findViewById(R.id.other_text);
            salePrice = itemView.findViewById(R.id.other_price);
            whoWants = itemView.findViewById(R.id.other_wants);
        }
    }

    public OtherItemAdapter(Activity context, List<RecyclerBean.RecyclerItemBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    private void initEvents(ViewHolder holder) {
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
                        AppNavigator.INSTANCE.openItemActivity(context, beanList.get(holder.getAdapterPosition()));
                    }
                });
    }
}
