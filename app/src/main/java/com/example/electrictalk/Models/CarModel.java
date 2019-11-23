package com.example.electrictalk.Models;

import java.util.Date;
import java.util.UUID;

public class CarModel {
    private String model;
    private String company;
    private int year;
    private int autonomy;
    private int batteryLeft;
    private String lastTechRevision;
    private UUID userId;
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
