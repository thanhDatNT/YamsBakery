package com.thanhdat.yams.Models;

public class Favorite {
    private int productThumb;
    private String productName;
    private double productPrice;
    private double productRating;
    private double productQuantity;

    public Favorite(int productThumb, String productName, double productPrice, double productRating, double productQuantity) {
        this.productThumb = productThumb;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productRating = productRating;
        this.productQuantity = productQuantity;
    }

    public int getProductThumb() {
        return productThumb;
    }

    public void setProductThumb(int productThumb) {
        this.productThumb = productThumb;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductRating() {
        return productRating;
    }

    public void setProductRating(double productRating) {
        this.productRating = productRating;
    }

    public double getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(double productQuantity) {
        this.productQuantity = productQuantity;
    }
}
