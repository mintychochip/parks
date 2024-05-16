package org.example;

import org.example.container.Pos;
import org.example.enums.Relation;

import java.util.Map;
import java.util.function.Consumer;

public interface Query extends Buildable<Query, Query.Builder> {
    static Query create() {
        return new QueryImpl();
    }

    static Builder builder() {
        return new QueryImpl.Builder();
    }

    int getRadius();

    Pos getCenter();

    Relation getRelation();

    TagContainer getTags();

    String toQueryString();

    interface Builder extends AbstractBuilder<Query> {
        Builder setRadius(int radius);

        Builder setCenter(Pos center);

        Builder setRelation(Relation relation);

        Builder setTags(Consumer<TagContainer.Builder> builderConsumer);

        Builder setTags(TagContainer tags);
    }
}
