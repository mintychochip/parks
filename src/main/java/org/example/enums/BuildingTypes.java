package org.example.enums;

public enum BuildingTypes {

    YES("yes"),
    HOUSE("house");

    private final String key;

    BuildingTypes(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
