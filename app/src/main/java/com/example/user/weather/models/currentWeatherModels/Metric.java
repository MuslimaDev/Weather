
package com.example.user.weather.models.currentWeatherModels;


import com.google.gson.annotations.SerializedName;

public class Metric {

    @SerializedName("Value")
    private Double value;

    @SerializedName("Unit")
    private String unit;


    public Double getValue() { return value; }

    public void setValue(Double value) { this.value = value; }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
