
package com.example.user.weather.models.searchPlaceModels;


import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("ID")

    private String iD;
    @SerializedName("LocalizedName")

    private String localizedName;

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

}
