package com.example.fitnessapp.model_class;

public class User {
    private String token;
    private String username;
    private String email;
    private int id;

    public User(String token, String username, String email, int id) {
        this.token = token;
        this.username = username;
        this.email = email;
        this.id = id;
    }
    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}
