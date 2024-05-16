package org.example;

import com.google.gson.annotations.SerializedName;
import org.example.container.NodeTags;
import org.example.enums.Relation;

public class Element {
    @SerializedName("type")
    private String type;

    @SerializedName("id")
    private long id;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lon")
    private double lon;

    @SerializedName("tags")
    private NodeTags tags;

    private String source;

    public String getType() {
        return type;
    }

    public Relation getNodeType() {
        return Enum.valueOf(Relation.class, type.toUpperCase());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getSource() {
        return source;
    }

    public NodeTags getTags() {
        return tags;
    }
}