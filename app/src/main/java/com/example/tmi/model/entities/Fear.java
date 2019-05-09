package com.example.tmi.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fear {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("confidence")
    @Expose
    private Integer confidence;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }
}
