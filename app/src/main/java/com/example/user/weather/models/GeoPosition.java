
package com.example.user.weather.models;


import com.google.gson.annotations.SerializedName;

public class GeoPosition {

    @SerializedName("Latitude")

    private Double latitude;
    @SerializedName("Longitude")

    private Double longitude;
    @SerializedName("Elevation")

    private Elevation elevation;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Elevation getElevation() {
        return elevation;
    }

    public void setElevation(Elevation elevation) {
        this.elevation = elevation;
    }

}
