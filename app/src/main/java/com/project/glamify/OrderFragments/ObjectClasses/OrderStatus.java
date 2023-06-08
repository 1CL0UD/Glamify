package com.project.glamify.OrderFragments.ObjectClasses;

public class OrderStatus {
    private String image;
    private String order;

    public OrderStatus(String order, String image) {
        this.image = image;
        this.order = order;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
