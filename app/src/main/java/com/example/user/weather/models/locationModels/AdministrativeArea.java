package com.example.user.weather.models.locationModels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 24.12.2018.
 */

public class AdministrativeArea {
    @SerializedName("ID")
    
    private String iD;
    @SerializedName("LocalizedName")
    
    private String localizedName;
    @SerializedName("EnglishName")
    
    private String englishName;
    @SerializedName("Level")
    
    private Integer level;
    @SerializedName("LocalizedType")
    
    private String localizedType;
    @SerializedName("EnglishType")
    
    private String englishType;
    @SerializedName("CountryID")
    
    private String countryID;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLocalizedType() {
        return localizedType;
    }

    public void setLocalizedType(String localizedType) {
        this.localizedType = localizedType;
    }

    public String getEnglishType() {
        return englishType;
    }

    public void setEnglishType(String englishType) {
        this.englishType = englishType;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }
}
