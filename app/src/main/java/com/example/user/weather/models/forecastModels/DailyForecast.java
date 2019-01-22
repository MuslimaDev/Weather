
package com.example.user.weather.models.forecastModels;


import com.google.gson.annotations.SerializedName;

import java.util.List;

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
}
