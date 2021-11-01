package com.thanhdat.yams.Models;

import android.widget.ImageView;

public class SeeReviewItem {

    private String reviewName, reviewText, sizeView, reviewSize, toppingView, reviewTopping;
    private int reviewAva, imvStar1, imvStar2, imvStar3, imvStar4, imvStar5, reviewProductThumb;

    public SeeReviewItem(String reviewName, String reviewText, String sizeView, String reviewSize, String reviewTopping, String toppingView, int reviewAva, int imvStar1, int imvStar2, int imvStar3, int imvStar4, int imvStar5, int reviewProductThumb) {
        this.reviewName = reviewName;
        this.reviewText = reviewText;
        this.sizeView = sizeView;
        this.reviewSize = reviewSize;
        this.reviewTopping = reviewTopping;
        this.toppingView = toppingView;
        this.reviewAva = reviewAva;
        this.imvStar1 = imvStar1;
        this.imvStar2 = imvStar2;
        this.imvStar3 = imvStar3;
        this.imvStar4 = imvStar4;
        this.imvStar5 = imvStar5;
        this.reviewProductThumb = reviewProductThumb;
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

    public String getSizeView() {
        return sizeView;
    }

    public void setSizeView(String sizeView) {
        this.sizeView = sizeView;
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

    public String getToppingView() {
        return toppingView;
    }

    public void setToppingView(String toppingView) {
        this.toppingView = toppingView;
    }

    public int getReviewAva() {
        return reviewAva;
    }

    public void setReviewAva(int reviewAva) {
        this.reviewAva = reviewAva;
    }

    public int getImvStar1() {
        return imvStar1;
    }

    public void setImvStar1(int imvStar1) {
        this.imvStar1 = imvStar1;
    }

    public int getImvStar2() {
        return imvStar2;
    }

    public void setImvStar2(int imvStar2) {
        this.imvStar2 = imvStar2;
    }

    public int getImvStar3() {
        return imvStar3;
    }

    public void setImvStar3(int imvStar3) {
        this.imvStar3 = imvStar3;
    }

    public int getImvStar4() {
        return imvStar4;
    }

    public void setImvStar4(int imvStar4) {
        this.imvStar4 = imvStar4;
    }

    public int getImvStar5() {
        return imvStar5;
    }

    public void setImvStar5(int imvStar5) {
        this.imvStar5 = imvStar5;
    }

    public int getReviewProductThumb() {
        return reviewProductThumb;
    }

    public void setReviewProductThumb(int reviewProductThumb) {
        this.reviewProductThumb = reviewProductThumb;
    }
}
