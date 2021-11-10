package com.thanhdat.yams.Models;

public class SimpleViewGroup {
    private int image;
    private String cate;

    public SimpleViewGroup(int image, String cate) {
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
