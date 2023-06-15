package com.project.glamify.OrderFragments.ObjectClasses;

public class OrderStatus {
    private String image;
    private String order;
    private String payment;

    public OrderStatus(String order, String image, String payment) {
        this.image = image;
        this.order = order;
        this.payment = payment;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
