package com.example.FoodFacilitySpring.entities;

public class DistanceCalculator {

    private Double userLatitude;
    private Double userLongitude;
    private Double latitude;
    private Double longitude;

    public DistanceCalculator(Double userLatitude, Double userLongitude, Double latitude, Double longitude) {
        this.userLatitude = userLatitude;
        this.userLongitude = userLongitude;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(Double userLatitude) {
        this.userLatitude = userLatitude;
    }

    public Double getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(Double userLongitude) {
        this.userLongitude = userLongitude;
    }

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

    public static double calculoDistancia(double userLatitude, double userLongitude, double latitude, double longitude) {
        double earthRadius = 6371.0;

        double dLat = Math.toRadians(userLatitude - latitude);
        double dLng = Math.toRadians(userLongitude - longitude);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(userLatitude))
                * Math.cos(Math.toRadians(latitude));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        return dist * 1000; // em metros
    }
}
