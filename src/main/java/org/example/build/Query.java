package org.example.build;

import org.example.CartesianCoordinate;
import org.example.enums.NodeType;

import java.util.HashMap;
import java.util.Map;

public class Query {

    private final String query;

    public static class QueryBuilder {
        private NodeType type;

        private int radius = -1;

        private CartesianCoordinate center;

        private Map<String, String> tags = new HashMap<>();

        private boolean convertWayToNode = false;

        public QueryBuilder setNodeType(NodeType type) {
            this.type = type;
            return this;
        }
        public QueryBuilder setConvertWayToNode(boolean convertWayToNode) {
            this.convertWayToNode = convertWayToNode;
            return this;
        }

        public QueryBuilder setRadius(int radius, CartesianCoordinate center) {
            this.radius = radius;
            this.center = center;
            return this;
        }

        public QueryBuilder addTag(String key, String val) {
            if(key == null || val == null) {
                return this;
            }
            if (!key.contains("\"")) {
                key = this.appendDoubleQuotes(key);
            }
            if (!val.contains("\"")) {
                val = this.appendDoubleQuotes(val);
            }
            this.tags.put(key,val);
            return this;
        }
        private String appendDoubleQuotes(String val) {
            return "\"" + val + "\"";
        }

        public Query build() {
            return new Query(this);
        }
    }

    public String getQuery() {
        return query;
    }

    private Query(QueryBuilder queryBuilder) {
        StringBuilder stringBuilder = new StringBuilder("[out:json];");
        stringBuilder.append(queryBuilder.type.getKey());
        if(queryBuilder.radius != -1) {
            String s = "(around:" + queryBuilder.radius + "," + queryBuilder.center.toString() + ")";
            stringBuilder.append(s);
        }
        if(queryBuilder.tags != null) {
            for (String s : queryBuilder.tags.keySet()) {
                stringBuilder.append(this.appendInsideBracket(s)).append("=").append(this.appendOutsideBracket(queryBuilder.tags.get(s)));
            }
            stringBuilder.append(";");
        }
        if(queryBuilder.convertWayToNode && queryBuilder.type == NodeType.WAY) {
            stringBuilder.append("node(w);");
        }
        stringBuilder.append("out;");
        this.query = stringBuilder.toString();
    }

    private String appendInsideBracket(String val) {
        return "[" + val;
    }
    private String appendOutsideBracket(String val) {
        return val + "]";
    }
}
