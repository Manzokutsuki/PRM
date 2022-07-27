package com.example.recycleview;

public class User {
    private String username;
    private String fullname;
    private String email;
    private int image;

    public User(String username, String fullname, String email) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
    }
    public User(String username, String fullname, String email, int image) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
