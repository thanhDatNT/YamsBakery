package com.thanhdat.yams.Models;

import android.widget.ImageView;

public class SeeReviewItem {

    private int reviewId;
    private String reviewText;
    private byte[] reviewImage;
    private double reviewRating;
    private String reviewSize;

    public SeeReviewItem(int reviewId, String reviewText, byte[] reviewImage, double reviewRating, String reviewSize) {
        this.reviewId = reviewId;
        this.reviewText = reviewText;
        this.reviewImage = reviewImage;
        this.reviewRating = reviewRating;
        this.reviewSize = reviewSize;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public byte[] getReviewImage() {
        return reviewImage;
    }

    public void setReviewImage(byte[] reviewImage) {
        this.reviewImage = reviewImage;
    }

    public double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(double reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewSize() {
        return reviewSize;
    }

    public void setReviewSize(String reviewSize) {
        this.reviewSize = reviewSize;
    }
}
