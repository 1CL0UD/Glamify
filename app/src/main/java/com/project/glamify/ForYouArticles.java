package com.project.glamify;

import java.io.Serializable;

public class ForYouArticles implements Serializable {
    private String image;
    private String title;
    private String article;
    private String source;

    public ForYouArticles(String image, String title, String article, String source) {
        this.image = image;
        this.title = title;
        this.article = article;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
