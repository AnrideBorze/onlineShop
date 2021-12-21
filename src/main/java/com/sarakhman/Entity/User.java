package com.sarakhman.Entity;

public class User {
    int id;
    String name;
    String email;
    String password;
    String salt;

    public User(int id, String name, String email, String password, String salt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.salt = salt;
    }
}
