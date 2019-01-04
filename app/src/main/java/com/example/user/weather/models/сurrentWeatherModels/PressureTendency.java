
package com.example.user.weather.models.сurrentWeatherModels;

import com.google.gson.annotations.SerializedName;

public class PressureTendency {

    @SerializedName("LocalizedText")
    
    private String localizedText;
    @SerializedName("Code")
    
    private String code;

    public String getLocalizedText() {
        return localizedText;
    }

    public void setLocalizedText(String localizedText) {
        this.localizedText = localizedText;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
