package com.example.electrictalk.Models;

import java.util.Date;
import java.util.UUID;

public class CarModel {
    private String model;
    private String company;
    private int year;
    private float autonomy;
    private int batteryLeft;
    private Date lastTechRevision;
    private UUID userId;
    private UUID id;

    public CarModel() {
    }

    public CarModel(String model, String company, int year, float autonomy, int batteryLeft, Date lastTechRevision, UUID userId, UUID id) {
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

    public float getAutonomy() {
        return autonomy;
    }

    public void setAutonomy(float autonomy) {
        this.autonomy = autonomy;
    }

    public int getBatteryLeft() {
        return batteryLeft;
    }

    public void setBatteryLeft(int batteryLeft) {
        this.batteryLeft = batteryLeft;
    }

    public Date getLastTechRevision() {
        return lastTechRevision;
    }

    public void setLastTechRevision(Date lastTechRevision) {
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
