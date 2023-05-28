package com.project.glamify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecomAdapter extends RecyclerView.Adapter<RecomAdapter.ImageViewHolder> {

    private Context mContext;
    private List<RecomProduct> mRecom;

    public RecomAdapter (Context context, List<RecomProduct> recom){
        mContext = context;
        mRecom = recom;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recom_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        RecomProduct recomCur = mRecom.get(position);
        holder.prod_name.setText(recomCur.getProduct_title());
        holder.prod_price.setText(recomCur.getProduct_price());
        Glide.with(mContext).load(recomCur.getProduct_image()).placeholder(R.drawable.makeup1).fitCenter().into(holder.prod_img);
    }

    @Override
    public int getItemCount() {
        return mRecom.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView prod_name, prod_price;
        public ImageView prod_img;

        public ImageViewHolder(@NonNull View itemView){
            super(itemView);
            prod_name = itemView.findViewById(R.id.prodName);
            prod_price = itemView.findViewById(R.id.prodPrice);
            prod_img = itemView.findViewById(R.id.prodImage);
        }

    }
}
