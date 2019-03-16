
package com.example.user.weather.model.currentWeatherModels;


import com.google.gson.annotations.SerializedName;

public class Speed {

    @SerializedName("Metric")
    private Metric metric;

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }
}
