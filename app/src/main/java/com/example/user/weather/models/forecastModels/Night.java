
package com.example.user.weather.models.forecastModels;


import com.google.gson.annotations.SerializedName;

public class Night {

    @SerializedName("Icon")
    private Integer icon;

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }
}
