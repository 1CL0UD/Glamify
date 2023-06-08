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

import com.squareup.picasso.Picasso;

import java.util.List;

public class ForYouArticleAdapter extends RecyclerView.Adapter<ForYouArticleAdapter.ArticlesViewHolder>{
    private List<ForYouArticles> forYouArticlesList;

    private OnItemClickListener onItemClickListener;

    public ForYouArticleAdapter(List<ForYouArticles> forYouArticlesList){
        this.forYouArticlesList = forYouArticlesList;
    }

    public void setForYouArticlesList(List<ForYouArticles> forYouArticlesList) {
        this.forYouArticlesList = forYouArticlesList;
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_material_card, parent, false);
        return new ArticlesViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder holder, int position) {
        ForYouArticles forYouArticles = forYouArticlesList.get(position);
        holder.articleTitle.setText(forYouArticles.getTitle());
        holder.articleSubtitle.setText(String.format(forYouArticles.getArticle()));
        String imagePath = forYouArticles.getImage();
        Picasso.get().load(imagePath).error(R.drawable.wedding_lakeside).into(holder.articleImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the corresponding product based on the clicked position
//                Toast.makeText(v.getContext(), "Item clicked at position: " + forYouArticles, Toast.LENGTH_SHORT).show();

                // Start the new activity and pass the product data
                Intent intent = new Intent(v.getContext(), ArticlePage.class);
                intent.putExtra("forYouArticles", forYouArticles);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return forYouArticlesList.size();
    }

    static class ArticlesViewHolder extends RecyclerView.ViewHolder {
        ImageView articleImage;
        TextView articleTitle;
        TextView articleSubtitle;


        ArticlesViewHolder(View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.card_img);
            articleTitle = itemView.findViewById(R.id.card_title);
            articleSubtitle = itemView.findViewById(R.id.card_sub);

        }
    }
}
