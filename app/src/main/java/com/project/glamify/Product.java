package com.project.glamify;

import java.io.Serializable;

public class Product implements Serializable {
    private String product_image;
    private String product_image1;
    private String product_image2;
    private String product_price;
    private String product_title;

    private String product_desc;

    public Product(){

    }
    public Product(String product_title, String product_price, String product_desc, String product_image, String product_image1, String product_image2) {
        this.product_image = product_image;
        this.product_price = product_price;
        this.product_title = product_title;
        this.product_desc = product_desc;
        this.product_image1 = product_image1;
        this.product_image2 = product_image2;
    }


    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getProduct_image1() {
        return product_image1;
    }

    public void setProduct_image1(String product_image1) {
        this.product_image1 = product_image1;
    }

    public String getProduct_image2() {
        return product_image2;
    }

    public void setProduct_image2(String product_image2) {
        this.product_image2 = product_image2;
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

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }
}
