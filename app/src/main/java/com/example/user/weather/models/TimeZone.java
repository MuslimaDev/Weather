
package com.example.user.weather.models;


import com.google.gson.annotations.SerializedName;

public class TimeZone {

    @SerializedName("Code")
    private String code;

    @SerializedName("Name")
    private String name;

    @SerializedName("GmtOffset")
    private Double gmtOffset;

    @SerializedName("IsDaylightSaving")
    private Boolean isDaylightSaving;

    @SerializedName("NextOffsetChange")
    private Object nextOffsetChange;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getGmtOffset() {
        return gmtOffset;
    }

    public void setGmtOffset(Double gmtOffset) {
        this.gmtOffset = gmtOffset;
    }

    public Boolean getIsDaylightSaving() {
        return isDaylightSaving;
    }

    public void setIsDaylightSaving(Boolean isDaylightSaving) {
        this.isDaylightSaving = isDaylightSaving;
    }

    public Object getNextOffsetChange() {
        return nextOffsetChange;
    }

    public void setNextOffsetChange(Object nextOffsetChange) {
        this.nextOffsetChange = nextOffsetChange;
    }

}
