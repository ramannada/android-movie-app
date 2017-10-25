package com.blogspot.ramannada.movieapp;

/**
 * Created by ramannada on 10/23/2017.
 */

public class User {
    private String username;
    private String email;
    private String password;
    private String gender;
    private int photo;
    private int id;

    public User(String username, String email, String password, String gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", photo=" + photo +
                ", id=" + id +
                '}';
    }
}
