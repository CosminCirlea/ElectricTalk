package com.example.electrictalk.Models;

import android.location.Location;

public class ChargingStationModel {
    private String name;
    private int totalSocket;
    private int freeSockets;
    private Location location;

    public ChargingStationModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSocket() {
        return totalSocket;
    }

    public void setTotalSocket(int totalSocket) {
        this.totalSocket = totalSocket;
    }

    public int getFreeSockets() {
        return freeSockets;
    }

    public void setFreeSockets(int freeSockets) {
        this.freeSockets = freeSockets;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
