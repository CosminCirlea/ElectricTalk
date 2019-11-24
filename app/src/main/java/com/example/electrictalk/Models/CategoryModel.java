package com.example.electrictalk.Models;

import com.google.gson.annotations.SerializedName;

public class CategoryModel {
    @SerializedName("title")
    private String title;
    @SerializedName("userId")
    private String userId;
    @SerializedName("parentId")
    private String parentId;
    @SerializedName("lastEdited")
    private String lastEdited;
    @SerializedName("id")
    private String id;

    public CategoryModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(String lastEdited) {
        this.lastEdited = lastEdited;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
