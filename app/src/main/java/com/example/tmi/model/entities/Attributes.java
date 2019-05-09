package com.example.tmi.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes {

    @SerializedName("face")
    @Expose
    private Face face;
    @SerializedName("gender")
    @Expose
    private Gender gender;
    @SerializedName("glasses")
    @Expose
    private Glasses glasses;
    @SerializedName("dark_glasses")
    @Expose
    private DarkGlasses darkGlasses;
    @SerializedName("smiling")
    @Expose
    private Smiling smiling;
    @SerializedName("age_est")
    @Expose
    private AgeEst ageEst;
    @SerializedName("mood")
    @Expose
    private Mood mood;
    @SerializedName("lips")
    @Expose
    private Lips lips;
    @SerializedName("neutral_mood")
    @Expose
    private NeutralMood neutralMood;
    @SerializedName("anger")
    @Expose
    private Anger anger;
    @SerializedName("disgust")
    @Expose
    private Disgust disgust;
    @SerializedName("fear")
    @Expose
    private Fear fear;
    @SerializedName("happiness")
    @Expose
    private Happiness happiness;
    @SerializedName("sadness")
    @Expose
    private Sadness sadness;
    @SerializedName("surprise")
    @Expose
    private Surprise surprise;
    @SerializedName("eyes")
    @Expose
    private Eyes eyes;

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Glasses getGlasses() {
        return glasses;
    }

    public void setGlasses(Glasses glasses) {
        this.glasses = glasses;
    }

    public DarkGlasses getDarkGlasses() {
        return darkGlasses;
    }

    public void setDarkGlasses(DarkGlasses darkGlasses) {
        this.darkGlasses = darkGlasses;
    }

    public Smiling getSmiling() {
        return smiling;
    }

    public void setSmiling(Smiling smiling) {
        this.smiling = smiling;
    }

    public AgeEst getAgeEst() {
        return ageEst;
    }

    public void setAgeEst(AgeEst ageEst) {
        this.ageEst = ageEst;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public Lips getLips() {
        return lips;
    }

    public void setLips(Lips lips) {
        this.lips = lips;
    }

    public NeutralMood getNeutralMood() {
        return neutralMood;
    }

    public void setNeutralMood(NeutralMood neutralMood) {
        this.neutralMood = neutralMood;
    }

    public Anger getAnger() {
        return anger;
    }

    public void setAnger(Anger anger) {
        this.anger = anger;
    }

    public Disgust getDisgust() {
        return disgust;
    }

    public void setDisgust(Disgust disgust) {
        this.disgust = disgust;
    }

    public Fear getFear() {
        return fear;
    }

    public void setFear(Fear fear) {
        this.fear = fear;
    }

    public Happiness getHappiness() {
        return happiness;
    }

    public void setHappiness(Happiness happiness) {
        this.happiness = happiness;
    }

    public Sadness getSadness() {
        return sadness;
    }

    public void setSadness(Sadness sadness) {
        this.sadness = sadness;
    }

    public Surprise getSurprise() {
        return surprise;
    }

    public void setSurprise(Surprise surprise) {
        this.surprise = surprise;
    }

    public Eyes getEyes() {
        return eyes;
    }

    public void setEyes(Eyes eyes) {
        this.eyes = eyes;
    }

}
