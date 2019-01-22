
package com.example.user.weather.models.forecastModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Forecast {

    @SerializedName("Headline")
    private Headline headline;

    @SerializedName("DailyForecasts")
    private List<DailyForecast> dailyForecasts = null;

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
