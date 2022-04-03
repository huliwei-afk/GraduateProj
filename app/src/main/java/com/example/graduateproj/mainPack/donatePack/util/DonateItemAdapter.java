package com.example.graduateproj.mainPack.donatePack.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graduateproj.R;

//public class DonateItemAdapter extends RecyclerView<DonateItemAdapter.ItemViewHolder> {
//
//    public DonateItemAdapter(@NonNull Context context) {
//        super(context);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donate_recyclerview_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        String content = dataList.get(position);
//        holder.tv.setText(content);
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataList == null ? 0 : dataList.size();
//    }
//
//    static class ItemViewHolder extends RecyclerView.ViewHolder {
//        ImageView saleIcon, saleImage, starIcon, commentIcon;
//
//        public ItemViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            saleIcon = itemView.findViewById(R.id.sale_icon);
//            saleImage = itemView.findViewById(R.id.sale_image);
//            starIcon = itemView.findViewById(R.id.donate_fragment_star);
//            commentIcon = itemView.findViewById(R.id.donate_fragment_comment);
//
//        }
//    }
//}
