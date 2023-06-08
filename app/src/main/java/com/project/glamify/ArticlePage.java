package com.project.glamify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;

public class ArticlePage extends AppCompatActivity {

    private ForYouArticles forYouArticles;
    private TextView title, article, source;

    private MaterialToolbar toolbar;

    private String articlePageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_page);

        Intent intent = getIntent();
        if (intent != null) {
            forYouArticles = (ForYouArticles) intent.getSerializableExtra("forYouArticles");

            if(forYouArticles != null){
                // Display the product details in your activity
                articlePageTitle = ((ForYouArticles) forYouArticles).getTitle();
                title = findViewById(R.id.article_title);
                title.setText(((ForYouArticles) forYouArticles).getTitle());
                article = findViewById(R.id.article_content);
                article.setText(((ForYouArticles) forYouArticles).getArticle());
                source = findViewById(R.id.article_source);
                source.setText(((ForYouArticles) forYouArticles).getSource());
            }else{

            }


            // ... set other views with the product data
        }

        toolbar = findViewById(R.id.topAppBar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(articlePageTitle);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}