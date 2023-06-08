package com.project.glamify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private OnItemClickListener onItemClickListener;

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
        String imagePath = product.getProduct_image();
        Picasso.get().load(imagePath).error(R.drawable.makeup1).into(holder.productImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the corresponding product based on the clicked position
//                Product clickedProduct = productList.get(position);
                Toast.makeText(v.getContext(), "Item clicked at position: " + product, Toast.LENGTH_SHORT).show();

                // Start the new activity and pass the product data
                Intent intent = new Intent(v.getContext(), ProductPage.class);
                intent.putExtra("product", product);
                v.getContext().startActivity(intent);
            }
        });
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


