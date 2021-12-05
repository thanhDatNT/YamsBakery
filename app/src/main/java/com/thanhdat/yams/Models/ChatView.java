package com.thanhdat.yams.Models;

import android.graphics.Bitmap;

public class ChatView {
    Bitmap bitmap;
    String message;

    public ChatView(Bitmap bitmap, String message) {
        this.bitmap = bitmap;
        this.message = message;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
