package com.thanhdat.yams.Models;

public class NewProduct {
    private int thumb;
    private String name, description, tag;
    private double price, rating;

    public NewProduct(int thumb, String name, String description, String tag, double price, double rating) {
        this.thumb = thumb;
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.price = price;
        this.rating = rating;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
