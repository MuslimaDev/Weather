package com.example.user.weather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by User on 24.12.2018.
 */

public class DailyForecast {
    @SerializedName("Date")
    
    private String date;
    @SerializedName("EpochDate")
    
    private Integer epochDate;
    @SerializedName("Temperature")
    
    private Temperature temperature;
    @SerializedName("Day")
    
    private Day day;
    @SerializedName("Night")
    
    private Night night;
    @SerializedName("Sources")
    
    private List<String> sources = null;
    @SerializedName("MobileLink")
    
    private String mobileLink;
    @SerializedName("Link")
    
    private String link;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getEpochDate() {
        return epochDate;
    }

    public void setEpochDate(Integer epochDate) {
        this.epochDate = epochDate;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Night getNight() {
        return night;
    }

    public void setNight(Night night) {
        this.night = night;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public String getMobileLink() {
        return mobileLink;
    }

    public void setMobileLink(String mobileLink) {
        this.mobileLink = mobileLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
