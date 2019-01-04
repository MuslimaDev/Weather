
package com.example.user.weather.models.сurrentWeatherModels;

import com.google.gson.annotations.SerializedName;

public class Minimum {

    @SerializedName("Metric")
    
    private Metric metric;
    @SerializedName("Imperial")
    
    private Imperial  imperial;

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric  metric) {
        this.metric = metric;
    }

    public Imperial getImperial() {
        return imperial;
    }

    public void setImperial(Imperial imperial) {
        this.imperial = imperial;
    }

}
