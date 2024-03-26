package org.example.enums;

import java.util.Arrays;

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
