package com.thanhdat.yams.Models;

public class Voucher {

    private int voucherThumb;
    private String voucherName;
    private String voucherHSD;

    public Voucher(int voucherThumb, String voucherName, String voucherHSD) {
        this.voucherThumb = voucherThumb;
        this.voucherName = voucherName;
        this.voucherHSD = voucherHSD;
    }

    public int getVoucherThumb() {
        return voucherThumb;
    }

    public void setVoucherThumb(int voucherThumb) {
        this.voucherThumb = voucherThumb;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getVoucherHSD() {
        return voucherHSD;
    }

    public void setVoucherHSD(String voucherHSD) {
        this.voucherHSD = voucherHSD;
    }
}
