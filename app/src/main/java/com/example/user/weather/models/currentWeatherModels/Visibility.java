
package com.example.user.weather.models.currentWeatherModels;


import com.google.gson.annotations.SerializedName;

public class Visibility {

    @SerializedName("Metric")
    private Metric metric;

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }
}
