package com.example.myapplication.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Report {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;
    @SerializedName("usage")
    @Expose
    private Usage usage;
    @SerializedName("operation_id")
    @Expose
    private String operationId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

}
