package com.thanhdat.yams.Models;

import java.util.List;

public class Cart {
    private int id, quantity, available;
    private String thumb, productName, productSize, topping;
    private double price;
    boolean isChecked;
    public Cart() {
    }

    public Cart(int id, String productName, int quantity, int available, String thumb, String productSize, String topping, double price) {
        this.id = id;
        this.quantity = quantity;
        this.available = available;
        this.thumb = thumb;
        this.productName = productName;
        this.productSize = productSize;
        this.topping = topping;
        this.price= price;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }
}