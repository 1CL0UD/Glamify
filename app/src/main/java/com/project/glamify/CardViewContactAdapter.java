package com.project.glamify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class CardViewContactAdapter extends RecyclerView.Adapter<CardViewContactAdapter.CardViewViewHolder>{
    private ArrayList<Contact> contactList;
    private Context context;

    public CardViewContactAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent,false);
        CardViewViewHolder viewHolder = new CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        Contact c = getContactList().get(position);
        Glide.with(context).load(c.getPhoto()).override(350,550).into(holder.imgPhoto);
        holder.title.setText(c.getName());
//        holder.btnCall.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
//            @Override
//            public void onItemClicked(View view, int position) {
//                Toast.makeText(context, "Call "+getContactList().get(position).getName(), Toast.LENGTH_SHORT).show();
//            }
//        }));
        holder.card_fy.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Clicked "+getContactList().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getContactList().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView title, subtitle;

        MaterialCardView card_fy;
        public CardViewViewHolder(View itemView) {
            super(itemView);
            card_fy = (MaterialCardView) itemView.findViewById(R.id.card_fy);
            imgPhoto = (ImageView) itemView.findViewById(R.id.card_img);
            title = (TextView) itemView.findViewById(R.id.card_title);
            subtitle = (TextView) itemView.findViewById(R.id.card_sub);;


        }
    }

}
