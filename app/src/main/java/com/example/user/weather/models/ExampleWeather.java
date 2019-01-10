package com.example.user.weather.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ExampleWeather {
    @SerializedName("Headline")
    
    private Headline headline;
    @SerializedName("DailyForecasts")
    
    private List<DailyForecast> dailyForecasts ;

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public List<DailyForecast> getDailyForecasts() {
        return dailyForecasts;
    }

    public void setDailyForecasts(List<DailyForecast> dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
    }
}
