package com.project.glamify.OrderFragments.AdapterClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.glamify.OnItemClickListener;
import com.project.glamify.OrderFragments.ObjectClasses.OrderStatus;
import com.project.glamify.Product;
import com.project.glamify.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class OrderStatusAdapter extends RecyclerView.Adapter<OrderStatusAdapter.ProductViewHolder> {

    private List<OrderStatus> orderStatusList;
    private OnItemClickListener onItemClickListener;

    public OrderStatusAdapter(List<OrderStatus> orderStatusList) {
        this.orderStatusList = orderStatusList;
    }
    public void setOrderStatusList(List<OrderStatus> orderStatusList) {
        this.orderStatusList = orderStatusList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_status_material_card, parent, false);
        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        OrderStatus orderStatus = orderStatusList.get(position);
        holder.orderName.setText(orderStatus.getOrder());
        holder.payment.setText(orderStatus.getPayment());
        String imagePath = orderStatus.getImage();
        Picasso.get().load(imagePath).error(R.drawable.makeup1).into(holder.orderImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return orderStatusList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView orderImage;
        TextView orderName;
        TextView payment;

        ProductViewHolder(View itemView) {
            super(itemView);
            orderImage = itemView.findViewById(R.id.order_img);
            orderName = itemView.findViewById(R.id.order_name);
            payment = itemView.findViewById(R.id.payment);


        }
    }
}


