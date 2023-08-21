package com.example.marcadorespersonalizadosgooglemapsapi;

class PlaceDetails {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getFormatted_phone_number() {
        return formatted_phone_number;
    }

    public void setFormatted_phone_number(String formatted_phone_number) {
        this.formatted_phone_number = formatted_phone_number;
    }
    private String formatted_phone_number; // Cambiar a formatted_phone_number

    public String getFormattedPhoneNumber() {
        return formatted_phone_number;
    }
    private double rating;  // Agregado rating


    // Getters y setters
    public double getRating() {
        return rating;
    }

    // Otros getters y setters
}
