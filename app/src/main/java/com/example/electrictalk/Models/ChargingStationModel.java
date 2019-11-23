package com.example.electrictalk.Models;

import android.graphics.PointF;
import android.location.Location;

import com.google.gson.annotations.SerializedName;

public class ChargingStationModel {
    @SerializedName("name")
    public String name;
    @SerializedName("totalSockets")
    private int totalSockets;
    @SerializedName("freeSockets")
    private int freeSockets;
    @SerializedName("location")
    private PointF location;
    @SerializedName("userId")
    private String userId;
    @SerializedName("oldStationId")
    private String oldStationId;
    @SerializedName("id")
    private String id;

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
}
