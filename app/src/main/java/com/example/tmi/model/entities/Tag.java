package com.example.tmi.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tag {
    @SerializedName("uids")
    @Expose
    private List<Object> uids = null;
    @SerializedName("label")
    @Expose
    private Object label;
    @SerializedName("confirmed")
    @Expose
    private Boolean confirmed;
    @SerializedName("manual")
    @Expose
    private Boolean manual;
    @SerializedName("width")
    @Expose
    private Double width;
    @SerializedName("height")
    @Expose
    private Double height;
    @SerializedName("yaw")
    @Expose
    private Integer yaw;
    @SerializedName("roll")
    @Expose
    private Integer roll;
    @SerializedName("pitch")
    @Expose
    private Integer pitch;
    @SerializedName("attributes")
    @Expose
    private Attributes attributes;
    @SerializedName("points")
    @Expose
    private Object points;
    @SerializedName("similarities")
    @Expose
    private Object similarities;
    @SerializedName("tid")
    @Expose
    private String tid;
    @SerializedName("recognizable")
    @Expose
    private Boolean recognizable;
    @SerializedName("center")
    @Expose
    private Center center;
    @SerializedName("eye_left")
    @Expose
    private Eye eyeLeft;
    @SerializedName("eye_right")
    @Expose
    private Eye eyeRight;
    @SerializedName("mouth_center")
    @Expose
    private MouthCenter mouthCenter;
    @SerializedName("nose")
    @Expose
    private Nose nose;

    public List<Object> getUids() {
        return uids;
    }

    public void setUids(List<Object> uids) {
        this.uids = uids;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
        this.label = label;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Boolean getManual() {
        return manual;
    }

    public void setManual(Boolean manual) {
        this.manual = manual;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getYaw() {
        return yaw;
    }

    public void setYaw(Integer yaw) {
        this.yaw = yaw;
    }

    public Integer getRoll() {
        return roll;
    }

    public void setRoll(Integer roll) {
        this.roll = roll;
    }

    public Integer getPitch() {
        return pitch;
    }

    public void setPitch(Integer pitch) {
        this.pitch = pitch;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Object getPoints() {
        return points;
    }

    public void setPoints(Object points) {
        this.points = points;
    }

    public Object getSimilarities() {
        return similarities;
    }

    public void setSimilarities(Object similarities) {
        this.similarities = similarities;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public Boolean getRecognizable() {
        return recognizable;
    }

    public void setRecognizable(Boolean recognizable) {
        this.recognizable = recognizable;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public Eye getEyeLeft() {
        return eyeLeft;
    }

    public void setEyeLeft(Eye eyeLeft) {
        this.eyeLeft = eyeLeft;
    }

    public Eye getEyeRight() {
        return eyeRight;
    }

    public void setEyeRight(Eye eyeRight) {
        this.eyeRight = eyeRight;
    }

    public MouthCenter getMouthCenter() {
        return mouthCenter;
    }

    public void setMouthCenter(MouthCenter mouthCenter) {
        this.mouthCenter = mouthCenter;
    }

    public Nose getNose() {
        return nose;
    }

    public void setNose(Nose nose) {
        this.nose = nose;
    }

}
