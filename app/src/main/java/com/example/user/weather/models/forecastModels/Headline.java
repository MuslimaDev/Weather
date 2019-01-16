
package com.example.user.weather.models.forecastModels;


import com.google.gson.annotations.SerializedName;

public class Headline {

    @SerializedName("EffectiveDate")

    private String effectiveDate;
    @SerializedName("EffectiveEpochDate")

    private Integer effectiveEpochDate;
    @SerializedName("Severity")

    private Integer severity;
    @SerializedName("Text")

    private String text;
    @SerializedName("Category")

    private String category;
    @SerializedName("EndDate")

    private String endDate;
    @SerializedName("EndEpochDate")

    private Integer endEpochDate;
    @SerializedName("MobileLink")

    private String mobileLink;
    @SerializedName("Link")

    private String link;

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Integer getEffectiveEpochDate() {
        return effectiveEpochDate;
    }

    public void setEffectiveEpochDate(Integer effectiveEpochDate) {
        this.effectiveEpochDate = effectiveEpochDate;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getEndEpochDate() {
        return endEpochDate;
    }

    public void setEndEpochDate(Integer endEpochDate) {
        this.endEpochDate = endEpochDate;
    }

    public String getMobileLink() {
        return mobileLink;
    }

    public void setMobileLink(String mobileLink) {
        this.mobileLink = mobileLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
