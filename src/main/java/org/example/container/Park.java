package org.example.container;

import org.example.CartesianCoordinate;
import org.example.QueryProcessor;
import org.example.ReverseGeocodingResponse;

import java.util.ArrayList;
import java.util.List;

public class Park {

    private double lat;
    private double lon;

    private Ward ward;

    private String name;

    private String description;
    private int radius;

    public static class ParkBuilder {
        private double lat = -1;
        private double lon = -1;
        private Ward ward = null;
        private String name = null;
        private String description = null;
        private int radius = -1;

        public ParkBuilder setLat(double lat) {
            this.lat = lat;
            return this;
        }

        public ParkBuilder setLon(double lon) {
            this.lon = lon;
            return this;
        }

        public ParkBuilder setCoordinate(CartesianCoordinate cartesianCoordinate) {
            this.lat = cartesianCoordinate.getLatitude();
            this.lon = cartesianCoordinate.getLongitude();
            return this;
        }

        public ParkBuilder setName(String name) {
            if (name != null) {
                this.name = name;
            }
            return this;
        }

        public ParkBuilder setDescription(String description) {
            if (description != null) {
                this.description = description;
            }
            return this;
        }

        public ParkBuilder setWard(Ward ward) {
            this.ward = ward;
            return this;
        }

        public ParkBuilder setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        public Park build() {
            return new Park(this);
        }
    }

    private Park(ParkBuilder parkBuilder) {
        this.radius = parkBuilder.radius;
        this.description = parkBuilder.description;

        this.ward = parkBuilder.ward;
        this.name = parkBuilder.name;

        this.lat = parkBuilder.lat;
        this.lon = parkBuilder.lon;
    }

    public Park(ReverseGeocodingResponse reverseGeocodingResponse, int radius, String name) {
        this.lat = Double.parseDouble(reverseGeocodingResponse.getLat());
        this.lon = Double.parseDouble(reverseGeocodingResponse.getLon());
        this.name = name;
        this.radius = radius;
    }


    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
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

    public int getRadius() {
        return radius;
    }

    public CartesianCoordinate getCartesian() {
        if(lat != -1 && lon != -1) {
            return new CartesianCoordinate(lat,lon);
        }
        return null;
    }
    @Override
    public String toString() {
        return name + " " + this.getCartesian().toString();
    }
}
