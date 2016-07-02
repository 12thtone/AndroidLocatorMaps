package com.mmmd.maher.androidlocator.model;

/**
 * Created by maher on 7/1/16.
 */
public class Places {

    private float longitude;
    private float latitude;
    private String locationTitle;
    private String locationAddress;
    private String locationImgUrl;

    public Places(float latitude, float longitude, String locationTitle, String locationAddress, String locationImgUrl) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.locationTitle = locationTitle;
        this.locationAddress = locationAddress;
        this.locationImgUrl = locationImgUrl;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getLocationImgUrl() {
        return locationImgUrl;
    }
}
