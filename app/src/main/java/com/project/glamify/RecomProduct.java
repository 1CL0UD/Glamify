package com.project.glamify;

public class RecomProduct {
    private String product_image;
    private String product_price;
    private String product_title;

    public RecomProduct(){

    }
    public RecomProduct(String product_image, String product_price, String product_title) {
        this.product_image = product_image;
        this.product_price = product_price;
        this.product_title = product_title;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }


}
