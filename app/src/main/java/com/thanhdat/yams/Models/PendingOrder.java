package com.thanhdat.yams.Models;

public class PendingOrder {
    private String orderName;
    private  int orderThumb;
    private String orderCode;
    private double orderPrice;

    public PendingOrder(String orderName, int orderThumb, String orderCode, double orderPrice) {
        this.orderName = orderName;
        this.orderThumb = orderThumb;
        this.orderCode = orderCode;
        this.orderPrice = orderPrice;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderThumb() {
        return orderThumb;
    }

    public void setOrderThumb(int orderThumb) {
        this.orderThumb = orderThumb;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
