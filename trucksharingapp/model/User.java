package com.example.trucksharingapp.model;

public class User {
    private int user_id;
    private String image;
    private String user_name;
    private  String password;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User(String user_name, String password, String image) {
        this.image=image;
        this.user_name = user_name;
        this.password = password;
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
