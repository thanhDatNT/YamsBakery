package com.thanhdat.yams.Models;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable{
    private int id;
    private String name, description, tag, thumbnail, buy, category, diet;
    private int checked, available;
    private double price, currentPrice, rating;
    private boolean isAvailable, isPromo, isFavorite;
    private List<String> topping;

    public Product() {
    }

    public Product(int id, String name, String description, String tag, String thumbnail, String buy, String category, String diet, int checked, int available, double price, double currentPrice, double rating, boolean isAvailable, boolean isPromo, boolean isFavorite, List<String> topping) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.thumbnail = thumbnail;
        this.buy = buy;
        this.category = category;
        this.diet = diet;
        this.checked = checked;
        this.available = available;
        this.price = price;
        this.currentPrice = currentPrice;
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.isPromo = isPromo;
        this.isFavorite = isFavorite;
        this.topping = topping;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public void setPromo(boolean promo) {
        isPromo = promo;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public List<String> getTopping() {
        return topping;
    }

    public void setTopping(List<String> topping) {
        this.topping = topping;
    }
}
