package org.example.enums;

public enum Relation {
    NODE("node"),
    WAY("way"),
    RELATION("relation");

    private final String key;

    Relation(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
