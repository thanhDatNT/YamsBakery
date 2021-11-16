package com.thanhdat.yams.Models;

public class Cart {
    public int CartID;
    private int CartThumb;
    private String CartName;
    private String CartSize;
    private double CartPrice;
    private int CartNumber;
    private int CartRemain;

    public Cart(int cartID, int cartThumb, String cartName, String cartSize, double cartPrice, int cartNumber, int cartRemain) {
        CartID = cartID;
        CartThumb = cartThumb;
        CartName = cartName;
        CartSize = cartSize;
        CartPrice = cartPrice;
        CartNumber = cartNumber;
        CartRemain = cartRemain;
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int cartID) {
        CartID = cartID;
    }

    public int getCartThumb() {
        return CartThumb;
    }

    public void setCartThumb(int cartThumb) {
        CartThumb = cartThumb;
    }

    public String getCartName() {
        return CartName;
    }

    public void setCartName(String cartName) {
        CartName = cartName;
    }

    public String getCartSize() {
        return CartSize;
    }

    public void setCartSize(String cartSize) {
        CartSize = cartSize;
    }

    public double getCartPrice() {
        return CartPrice;
    }

    public void setCartPrice(double cartPrice) {
        CartPrice = cartPrice;
    }

    public int getCartNumber() {
        return CartNumber;
    }

    public void setCartNumber(int cartNumber) {
        CartNumber = cartNumber;
    }

    public int getCartRemain() {
        return CartRemain;
    }

    public void setCartRemain(int cartRemain) {
        CartRemain = cartRemain;
    }
}