package com.example.user.weather.models.locationModels;

import com.google.gson.annotations.SerializedName;

public class Region {
    @SerializedName("ID")
    
    private String iD;
    @SerializedName("LocalizedName")
    
    private String localizedName;
    @SerializedName("EnglishName")
    
    private String englishName;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

}