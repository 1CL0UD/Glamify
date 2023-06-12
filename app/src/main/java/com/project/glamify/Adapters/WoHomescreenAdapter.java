package com.project.glamify.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.glamify.ArticlePage;
import com.project.glamify.ForYouArticles;
import com.project.glamify.ObjectClasses.WeddingOrganizer;
import com.project.glamify.OnItemClickListener;
import com.project.glamify.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WoHomescreenAdapter extends RecyclerView.Adapter<WoHomescreenAdapter.ArticlesViewHolder>{
    private List<WeddingOrganizer> woList;

    private OnItemClickListener onItemClickListener;
    public WoHomescreenAdapter(List<WeddingOrganizer> woList){
        this.woList = woList;
    }

    public void setWoList(List<WeddingOrganizer> woList) {
        this.woList = woList;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wo_home_material_card, parent, false);
        return new ArticlesViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
        WeddingOrganizer woObject = woList.get(position);
        holder.woTitle.setText(woObject.getTitle());
        holder.woSub.setText(String.format(woObject.getArticle()));
        String imagePath = woObject.getImage();
        Picasso.get().load(imagePath).error(R.drawable.wedding_lakeside).into(holder.woImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the corresponding product based on the clicked position
//                Toast.makeText(v.getContext(), "Item clicked at position: " + forYouArticles, Toast.LENGTH_SHORT).show();

                // Start the new activity and pass the product data
                Intent intent = new Intent(v.getContext(), ArticlePage.class);
                intent.putExtra("woObject", woObject);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return woList.size();
    }

    static class ArticlesViewHolder extends RecyclerView.ViewHolder {
        ImageView woImg;
        TextView woTitle;
        TextView woSub;
        ArticlesViewHolder(View itemView) {
            super(itemView);
            woImg = itemView.findViewById(R.id.woImg);
            woTitle = itemView.findViewById(R.id.woTitle);
            woSub = itemView.findViewById(R.id.woSub);
        }
    }
}
