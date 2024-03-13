package org.example;

public class Node {

    private final long id;
    private final double lat;
    private final double longitude;
    public Node(long id, double lat, double longitude) {
        this.id = id;
        this.lat = lat;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLongitude() {
        return longitude;
    }
}
