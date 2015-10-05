package com.example.opeyemi.storytime.DataModels;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by opeyemi on 03/08/2015.
 */
public class Character extends RealmObject {
    // Character physcial attributes
    private String name = "";
    private String age = "";
    private String storyOfOrigin = "";
    private String height = "";
    private String race = "";

    //Character personality attributes.
    private String openness = "";
    private String conscientiousness = "";
    private String extraversion = "";
    private String agreeableness = "";
    private String neuroticism = "";

    //Character's getter and setters for attributes.
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description;
    }

    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStoryOfOrigin() {
        return storyOfOrigin;
    }

    public void setStoryOfOrigin(String storyOfOrigin) {
        this.storyOfOrigin = storyOfOrigin;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    private String occupation;

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOpenness() {
        return openness;
    }

    public void setOpenness(String openness) {
        this.openness = openness;
    }
    public String getConscientiousness() {
        return conscientiousness;
    }

    public void setConscientiousness(String conscientiousness) {
        this.conscientiousness = conscientiousness;
    }
    public String getExtraversion() {
        return extraversion;
    }

    public void setExtraversion(String extraversion) {
        this.extraversion = extraversion;
    }
    public String getAgreeableness() {
        return agreeableness;
    }

    public void setAgreeableness(String agreeableness) {
        this.agreeableness = agreeableness;
    }
    public String getNeuroticism() {
        return neuroticism;
    }

    public void setNeuroticism(String neuroticism) {
        this.neuroticism = neuroticism;
    }
}