package com.example.electrictalk.Models;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class UserModel {
    @SerializedName("firstName")
    private String firstname;
    @SerializedName("lastName")
    private String name;
    @SerializedName("email")
    private String email;
    private String password;
    private UUID id;

    public UserModel() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
