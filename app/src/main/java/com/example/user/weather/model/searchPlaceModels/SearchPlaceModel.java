
package com.example.user.weather.model.searchPlaceModels;


import com.google.gson.annotations.SerializedName;

public class SearchPlaceModel {

    @SerializedName("Version")
    private Integer version;

    @SerializedName("Key")
    private String key;

    @SerializedName("Type")
    private String type;

    @SerializedName("Rank")
    private Integer rank;

    @SerializedName("LocalizedName")
    private String localizedName;

    @SerializedName("Country")
    private Country country;

    @SerializedName("AdministrativeArea")
    private AdministrativeArea administrativeArea;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public AdministrativeArea getAdministrativeArea() {
        return administrativeArea;
    }

    public void setAdministrativeArea(AdministrativeArea administrativeArea) {
        this.administrativeArea = administrativeArea;
    }

}
