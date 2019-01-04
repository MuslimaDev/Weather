package com.example.user.weather.models.locationModels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 24.12.2018.
 */

public class Imperial {
    @SerializedName("Value")
    
    private Integer value;
    @SerializedName("Unit")
    
    private String unit;
    @SerializedName("UnitType")
    
    private Integer unitType;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
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
