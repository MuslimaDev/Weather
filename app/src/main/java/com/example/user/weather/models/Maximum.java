package com.example.user.weather.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 24.12.2018.
 */

public class Maximum {
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
