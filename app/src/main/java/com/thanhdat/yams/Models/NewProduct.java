package com.thanhdat.yams.Models;

public class NewProduct {
    private int thumb;
    private String name, price, rating, tag;

    public NewProduct(int thumb, String name, String price, String rating, String tag) {
        this.thumb = thumb;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.tag = tag;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
