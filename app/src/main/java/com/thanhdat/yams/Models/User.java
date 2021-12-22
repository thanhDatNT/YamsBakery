package com.thanhdat.yams.Models;

public class User {
    String name, phone, photo, email;

    public User() {
    }

    public User(String name, String phone, String photo, String mail) {
        this.name = name;
        this.phone = phone;
        this.photo = photo;
        this.email = mail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
