
package com.example.user.weather.models.currentWeatherModels;

import com.google.gson.annotations.SerializedName;

public class CurrentModel {

    @SerializedName("WeatherIcon")
    private Integer weatherIcon;

    @SerializedName("Temperature")
    private Temperature temperature;

    @SerializedName("RealFeelTemperature")
    private RealFeelTemperature realFeelTemperature;

    @SerializedName("Wind")
    private Wind wind;

    @SerializedName("Visibility")
    private Visibility visibility;

    @SerializedName("Link")
    private String link;

    public Integer getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(Integer weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public RealFeelTemperature getRealFeelTemperature() {
        return realFeelTemperature;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
