package com.example.electrictalk.Models;

public class LocationModel {
    public boolean isEmpty;
    public double x;
    public double y;

    public LocationModel(boolean isEmpty, double x, double y) {
        this.isEmpty = isEmpty;
        this.x = x;
        this.y = y;
    }

    public LocationModel() {
    }
}
