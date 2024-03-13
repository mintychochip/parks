package org.example;

public class CartesianCoordinate {

    private final double latitude;

    private final double longitude;
    public CartesianCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return this.latitude + ", " + this.longitude;
    }
}
