package com.project.glamify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CardViewProfileAdapter extends RecyclerView.Adapter<CardViewProfileAdapter.CardViewViewHolder>{
    private ArrayList<Profile> profileList;
    private Context context;

    public CardViewProfileAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Profile> getProfileList() {
        return profileList;
    }

    public void setProfileList(ArrayList<Profile> profileList) {
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_menu_item, parent,false);
        CardViewViewHolder viewHolder = new CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        Profile p = getProfileList().get(position);
        Glide.with(context).load(p.getIcon()).override(350,550).into(holder.imgPhoto);
        holder.btnMessage.setText(p.getName());
//        holder.btnCall.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
//            @Override
//            public void onItemClicked(View view, int position) {
//                Toast.makeText(context, "Call "+getContactList().get(position).getName(), Toast.LENGTH_SHORT).show();
//            }
//        }));
        holder.btnMessage.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Message "+getProfileList().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getProfileList().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName;
        Button btnMessage;
        public CardViewViewHolder(View itemView) {
            super(itemView);
            imgPhoto = (ImageView)itemView.findViewById(R.id.img_item_photo);
            btnMessage = (Button)itemView.findViewById(R.id.btn_message);
        }
    }

}
