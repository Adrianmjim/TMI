package com.example.tmi.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Center {

    @SerializedName("x")
    @Expose
    private Float x;
    @SerializedName("y")
    @Expose
    private Float y;

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }
}
