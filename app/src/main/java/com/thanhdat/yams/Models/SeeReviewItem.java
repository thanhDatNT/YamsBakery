package com.thanhdat.yams.Models;

import android.widget.ImageView;

public class SeeReviewItem {

    private int id;
    private double rating;
    private byte[] profile;
    private String name;
    private String content;
    private byte[] image;
    private String size;

    public SeeReviewItem(int id, double rating, byte[] profile, String name, String content, byte[] image, String size) {
        this.id = id;
        this.rating = rating;
        this.profile = profile;
        this.name = name;
        this.content = content;
        this.image = image;
        this.size = size;
    }

    public SeeReviewItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public byte[] getProfile() {
        return profile;
    }

    public void setProfile(byte[] profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
