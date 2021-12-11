package com.thanhdat.yams.Models;

import java.io.Serializable;

public class PreviousOrder implements Serializable {
    private String previousName;
    private int previousThumb;
    private String previousContent;
    private double previousPrice;
    private int previousQuantity;

    public PreviousOrder(String previousName, int previousThumb, String previousContent, double previousPrice, int previousQuantity) {
        this.previousName = previousName;
        this.previousThumb = previousThumb;
        this.previousContent = previousContent;
        this.previousPrice = previousPrice;
        this.previousQuantity = previousQuantity;
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

    public double getPreviousQuantity() {
        return previousQuantity;
    }

    public void setPreviousQuantity(int previousQuantity) {
        this.previousQuantity = previousQuantity;
    }
}