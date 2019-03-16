
package com.example.user.weather.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {

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

    @SerializedName("EnglishName")
    private String englishName;

    @SerializedName("PrimaryPostalCode")
    private String primaryPostalCode;

    @SerializedName("Region")
    private Region region;

    @SerializedName("Country")
    private Country country;

    @SerializedName("AdministrativeArea")
    private AdministrativeArea administrativeArea;

    @SerializedName("TimeZone")
    private TimeZone timeZone;

    @SerializedName("GeoPosition")
    private GeoPosition geoPosition;

    @SerializedName("IsAlias")
    private Boolean isAlias;

    @SerializedName("SupplementalAdminAreas")
    private List<Object> supplementalAdminAreas = null;

    @SerializedName("DataSets")
    private List<Object> dataSets = null;

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

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getPrimaryPostalCode() {
        return primaryPostalCode;
    }

    public void setPrimaryPostalCode(String primaryPostalCode) {
        this.primaryPostalCode = primaryPostalCode;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public Boolean getIsAlias() {
        return isAlias;
    }

    public void setIsAlias(Boolean isAlias) {
        this.isAlias = isAlias;
    }

    public List<Object> getSupplementalAdminAreas() {
        return supplementalAdminAreas;
    }

    public void setSupplementalAdminAreas(List<Object> supplementalAdminAreas) {
        this.supplementalAdminAreas = supplementalAdminAreas;
    }

    public List<Object> getDataSets() {
        return dataSets;
    }

    public void setDataSets(List<Object> dataSets) {
        this.dataSets = dataSets;
    }

}
