package com.thanhdat.yams.Models;

public class TextThumbView {
    private int thumb;
    private String text;

    public TextThumbView(int image, String text) {
        this.thumb = image;
        this.text = text;
    }

    public int getImage() {
        return thumb;
    }

    public void setImage(int image) {
        this.thumb = image;
    }

    public String getCate() {
        return text;
    }

    public void setCate(String cate) {
        this.text = cate;
    }
}
