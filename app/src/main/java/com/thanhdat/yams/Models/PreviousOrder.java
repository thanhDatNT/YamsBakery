package com.thanhdat.yams.Models;

public class PreviousOrder {
    private String previousName;
    private  int previousThumb;
    private String previousContent;
    private double previousPrice;

    public PreviousOrder(String previousName, int previousThumb, String previousContent, double previousPrice) {
        this.previousName = previousName;
        this.previousThumb = previousThumb;
        this.previousContent = previousContent;
        this.previousPrice = previousPrice;
    }

    public String getPreviousName() {
        return previousName;
    }

    public void setPreviousName(String previousName) {
        this.previousName = previousName;
    }

    public int getPreviousThumb() {
        return previousThumb;
    }

    public void setPreviousThumb(int previousThumb) {
        this.previousThumb = previousThumb;
    }

    public String getPreviousContent() {
        return previousContent;
    }

    public void setPreviousContent(String previousContent) {
        this.previousContent = previousContent;
    }

    public double getPreviousPrice() {
        return previousPrice;
    }

    public void setPreviousPrice(double previousPrice) {
        this.previousPrice = previousPrice;
    }
}
