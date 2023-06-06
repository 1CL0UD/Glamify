package com.project.glamify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopCarouselAdapter extends RecyclerView.Adapter<ShopCarouselAdapter.ProductViewHolder> {

    private List<ShopCarousel> shopCarouselList;

    public ShopCarouselAdapter(List<ShopCarousel> productList) {
        this.shopCarouselList = productList;
    }
    public void setShopCarouselList(List<ShopCarousel> shopCarouselList) {
        this.shopCarouselList = shopCarouselList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_photo_carousel, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ShopCarousel shopCarousel = shopCarouselList.get(position);
        Picasso.get().load(shopCarousel.getImage()).error(R.drawable.wedding_lakeside).into(holder.carouselImageView);
    }

    @Override
    public int getItemCount() {
        return shopCarouselList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView carouselImageView;
        ProductViewHolder(View itemView) {
            super(itemView);
            carouselImageView = itemView.findViewById(R.id.shop_carousel);
        }
    }
}


