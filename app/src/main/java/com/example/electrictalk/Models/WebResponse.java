package com.example.electrictalk.Models;

import com.google.gson.annotations.SerializedName;

public class WebResponse<T> {
    @SerializedName("Data")
    private T data;
    @SerializedName("Success")
    private boolean success;
    @SerializedName("StatusCode")
    private int statusCode;

    public WebResponse() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean isSuccessful() {
        return success;
    }
}
