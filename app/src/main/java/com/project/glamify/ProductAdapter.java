package com.project.glamify;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_material_card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productTitleTextView.setText(product.getProduct_title());
        holder.productPriceTextView.setText(String.format("Rp %s", product.getProduct_price()));
        holder.productTitleTextView.setText(product.getProduct_title());
        Picasso.get().load(product.getProduct_image()).error(R.drawable.makeup1).into(holder.productImageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productTitleTextView;
        TextView productPriceTextView;


        ProductViewHolder(View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.prod_img);
            productTitleTextView = itemView.findViewById(R.id.prod_title);
            productPriceTextView = itemView.findViewById(R.id.prod_price);

        }
    }
}


