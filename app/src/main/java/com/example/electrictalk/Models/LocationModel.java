package com.example.electrictalk.Models;

import com.google.gson.annotations.SerializedName;

public class LocationModel {
    @SerializedName("isEmpty")
    public boolean isEmpty;
    @SerializedName("x")
    public float x;
    @SerializedName("y")
    public float y;

    public LocationModel(float x, float y) {
        this.x = x;
        this.y = y;
//        isEmpty = false;po
    }

    public LocationModel(boolean isEmpty, float x, float y) {
//        this.isEmpty = isEmpty;
        this.x = x;
        this.y = y;
    }

    public LocationModel() {
    }
}
