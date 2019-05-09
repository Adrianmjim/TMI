package com.example.tmi.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Eye {

    @SerializedName("x")
    @Expose
    private Double x;
    @SerializedName("y")
    @Expose
    private Double y;
    @SerializedName("confidence")
    @Expose
    private Integer confidence;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
