package com.example.electrictalk.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

public class CarModel {
    @SerializedName("model")
    private String model;
    @SerializedName("company")
    private String company;
    @SerializedName("year")
    private int year;
    @SerializedName("autonomy")
    private int autonomy;
    @SerializedName("batteryLeft")
    private int batteryLeft;
    @SerializedName("lastTechRevision")
    private String lastTechRevision;
    @SerializedName("userId")
    private UUID userId;
    @SerializedName("id")
    private UUID id;

    public CarModel() {
    }

    public CarModel(String model, String company, int year, int autonomy, int batteryLeft, String lastTechRevision, UUID userId, UUID id) {
        this.model = model;
        this.company = company;
        this.year = year;
        this.autonomy = autonomy;
        this.batteryLeft = batteryLeft;
        this.lastTechRevision = lastTechRevision;
        this.userId = userId;
        this.id = id;
    }

    public CarModel(String model, String company, int batteryLeft) {
        this.model = model;
        this.company = company;
        this.batteryLeft = batteryLeft;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAutonomy() {
        return autonomy;
    }

    public void setAutonomy(int autonomy) {
        this.autonomy = autonomy;
    }

    public int getBatteryLeft() {
        return batteryLeft;
    }

    public void setBatteryLeft(int batteryLeft) {
        this.batteryLeft = batteryLeft;
    }

    public String getLastTechRevision() {
        return lastTechRevision;
    }

    public void setLastTechRevision(String lastTechRevision) {
        this.lastTechRevision = lastTechRevision;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
