package com.example.marcadorespersonalizadosgooglemapsapi;

import com.google.android.gms.maps.model.LatLng;

class PlaceInfo {
    private String name;
    private LatLng location;
    private String logoUrl;
    private String formattedPhoneNumber;
    private String placeId;
    private double rating;  // Agregado rating

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

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public PlaceInfo(String name, LatLng location, String logoUrl, String formattedPhoneNumber, String placeId, double rating) {
        this.name = name;
        this.location = location;
        this.logoUrl = logoUrl;
        this.formattedPhoneNumber = formattedPhoneNumber;
        this.placeId = placeId;
        this.rating = rating;
    }

    // Getters y setters
    public double getRating() {
        return rating;
    }

    // Otros getters y setters
}