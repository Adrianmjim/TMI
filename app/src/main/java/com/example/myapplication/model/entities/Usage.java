package com.example.myapplication.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usage {
    @SerializedName("used")
    @Expose
    private Integer used;
    @SerializedName("remaining")
    @Expose
    private Integer remaining;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("reset_time")
    @Expose
    private Integer resetTime;
    @SerializedName("reset_time_text")
    @Expose
    private String resetTimeText;

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getResetTime() {
        return resetTime;
    }

    public void setResetTime(Integer resetTime) {
        this.resetTime = resetTime;
    }

    public String getResetTimeText() {
        return resetTimeText;
    }

    public void setResetTimeText(String resetTimeText) {
        this.resetTimeText = resetTimeText;
    }
}
