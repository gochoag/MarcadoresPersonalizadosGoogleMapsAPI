package com.example.marcadorespersonalizadosgooglemapsapi;

import com.google.android.gms.maps.model.LatLng;

public class PlaceInfo {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    private String name;
    private LatLng location;
    private String logoUrl;
    private String openingHours;

    public PlaceInfo(String name, LatLng location, String logoUrl, String openingHours) {
        this.name = name;
        this.location = location;
        this.logoUrl = logoUrl;
        this.openingHours = openingHours;
    }


}