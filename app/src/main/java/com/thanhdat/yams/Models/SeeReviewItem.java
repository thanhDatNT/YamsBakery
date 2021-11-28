package com.thanhdat.yams.Models;

import android.widget.ImageView;

public class SeeReviewItem {

    private String reviewName, reviewText, reviewSize, reviewTopping;
    private int reviewAva, reviewProductThumb;
    private double reviewRating;

    public SeeReviewItem(String reviewName, String reviewText, String reviewSize, String reviewTopping, int reviewAva, int reviewProductThumb, double reviewRating) {
        this.reviewName = reviewName;
        this.reviewText = reviewText;
        this.reviewSize = reviewSize;
        this.reviewTopping = reviewTopping;
        this.reviewAva = reviewAva;
        this.reviewProductThumb = reviewProductThumb;
        this.reviewRating = reviewRating;
    }

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getReviewSize() {
        return reviewSize;
    }

    public void setReviewSize(String reviewSize) {
        this.reviewSize = reviewSize;
    }

    public String getReviewTopping() {
        return reviewTopping;
    }

    public void setReviewTopping(String reviewTopping) {
        this.reviewTopping = reviewTopping;
    }

    public int getReviewAva() {
        return reviewAva;
    }

    public void setReviewAva(int reviewAva) {
        this.reviewAva = reviewAva;
    }

    public int getReviewProductThumb() {
        return reviewProductThumb;
    }

    public void setReviewProductThumb(int reviewProductThumb) {
        this.reviewProductThumb = reviewProductThumb;
    }

    public double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(double reviewRating) {
        this.reviewRating = reviewRating;
    }
}
