package com.thanhdat.yams.Models;

import java.io.Serializable;

public class Voucher implements Serializable {
    private boolean type;
    private int thumb;
    private String name, expireTime;
    private double value;

//  Type: true: Delivery Fee, false: Order items
    public Voucher(boolean type, int thumb, String name, String expireTime, double value) {
        this.type = type;
        this.thumb = thumb;
        this.name = name;
        this.expireTime = expireTime;
        this.value = value;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
