package com.thanhdat.yams.Models;

import java.io.Serializable;

public class PendingOrder implements Serializable {
    private int id;
    private String orderName;
    private  String orderThumb;
    private String orderCode;
    private double orderPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PendingOrder(int id, String orderName, String orderThumb, String orderCode, double orderPrice) {
        this.orderName = orderName;
        this.orderThumb = orderThumb;
        this.orderCode = orderCode;
        this.orderPrice = orderPrice;
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderThumb() {
        return orderThumb;
    }

    public void setOrderThumb(String orderThumb) {
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
