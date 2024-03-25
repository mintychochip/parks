package org.example.enums;

public enum NodeType {
    NODE("node"),
    WAY("way"),
    RELATION("relation");

    private final String key;

    NodeType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
