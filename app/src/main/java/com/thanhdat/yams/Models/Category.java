package com.thanhdat.yams.Models;

public class Category {
    private int image;
    private String cate;

    public Category(int image, String cate) {
        this.image = image;
        this.cate = cate;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }
}
