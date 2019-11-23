package com.example.electrictalk.Models;

import android.graphics.PointF;
import android.location.Location;

public class ChargingStationModel {
    private String name;
    private int totalSockets;
    private int freeSockets;
    private PointF location;
    private String userId;
    private String oldStationId;
    private String id;
    private LocationModel locationModel;

    public ChargingStationModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSocket() {
        return totalSockets;
    }

    public void setTotalSocket(int totalSocket) {
        this.totalSockets = totalSocket;
    }

    public int getFreeSockets() {
        return freeSockets;
    }

    public void setFreeSockets(int freeSockets) {
        this.freeSockets = freeSockets;
    }

    public PointF getLocation() {
        return location;
    }

    public void setLocation(PointF location) {
        this.location = location;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldStationId() {
        return oldStationId;
    }

    public void setOldStationId(String oldStationId) {
        this.oldStationId = oldStationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocationModel getLocationModel() {
        return locationModel;
    }

    public void setLocationModel(LocationModel locationModel) {
        this.locationModel = locationModel;
    }
}
