
package com.example.user.weather.model.forecastModels;


import com.google.gson.annotations.SerializedName;

public class Minimum {

    @SerializedName("Value")
    private Double value;

    @SerializedName("Unit")
    private String unit;

    @SerializedName("UnitType")
    private Integer unitType;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

}
