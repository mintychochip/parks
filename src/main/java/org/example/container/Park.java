package org.example.container;

import org.example.CartesianCoordinate;

public class Park {

    private final CartesianCoordinate cartesianCoordinate;

    private final Ward ward;

    private final String name;

    private final String description;

    private Park(double lat, double lon, Ward ward, String name, String description) {
        this.cartesianCoordinate = new CartesianCoordinate(lat,lon);
        this.ward = ward;
        this.name = name;
        this.description = description;
    }

    public Park(CartesianCoordinate cartesianCoordinate, Ward ward, String name, String desricription) {
        this.cartesianCoordinate = cartesianCoordinate;
        this.ward = ward;
        this.name = name;
        this.description = desricription;
    }

    public CartesianCoordinate getCartesianCoordinate() {
        return cartesianCoordinate;
    }

    public Ward getWard() {
        return ward;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
