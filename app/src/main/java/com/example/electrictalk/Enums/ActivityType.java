package com.example.electrictalk.Enums;

public enum ActivityType {
    DETAILS(0),
    EDIT(1);

    private int statusValue;

    private ActivityType (int statusValue) {
        this. statusValue = statusValue;
    }
}
