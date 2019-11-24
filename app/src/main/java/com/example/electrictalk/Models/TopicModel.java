package com.example.electrictalk.Models;

import com.google.gson.annotations.SerializedName;

public class TopicModel {
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("userId")
    private String userId;
    @SerializedName("categoryId")
    private String categoryId;
    @SerializedName("created")
    private String created;
    @SerializedName("lastEdited")
    private String lastEdited;
    @SerializedName("id")
    private String id;

    public TopicModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
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
