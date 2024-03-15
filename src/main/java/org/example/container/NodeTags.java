package org.example.container;

import com.google.gson.annotations.SerializedName;

public class NodeTags {

    @SerializedName("addr:state")
    private String stateInitials;
    @SerializedName("building")
    private String buildingtag;
    @SerializedName("gnis:county_name")
    private String countyName;
    @SerializedName("gnis:feature_id")
    private String featureID;
    @SerializedName("gnis:import_uuid")
    private String importUUID;
    @SerializedName("name")
    private String name;
    @SerializedName("gnis:reviewed")
    private String reviewed;

    public String getStateInitials() {
        return stateInitials;
    }

    public String getBuildingtag() {
        return buildingtag;
    }

    public String getCountyName() {
        return countyName;
    }

    public String getFeatureID() {
        return featureID;
    }

    public String getImportUUID() {
        return importUUID;
    }

    public String getName() {
        return name;
    }

    public String getReviewed() {
        return reviewed;
    }
}
