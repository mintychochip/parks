package org.example;

import org.example.container.Pos;
import org.example.enums.Relation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

final class QueryImpl implements Query {

    private final int radius;

    private final Pos center;

    private final Relation relation;

    private final TagContainer tags;

    QueryImpl() {
        this.radius = 1;
        this.center = null;
        this.relation = Relation.NODE;
        this.tags = TagContainer.create();
    }
    QueryImpl(Builder builder) {
        this.radius = builder.radius;
        this.center = builder.center;
        this.relation = builder.relation;
        this.tags = builder.tags;
    }

    public Query.Builder toBuilder() {
        return new Builder(this);
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public Pos getCenter() {
        return center;
    }

    @Override
    public Relation getRelation() {
        return relation;
    }

    @Override
    public TagContainer getTags() {
        return tags;
    }

    static final class Builder implements Query.Builder {
        private int radius;

        private Pos center;

        private Relation relation;

        private TagContainer tags;

        Builder() {
            this(new QueryImpl());
        }

        Builder(QueryImpl query) {
            radius = query.radius;
            center = query.center;
            relation = query.relation;
            tags = query.tags;
        }
        @Override
        public Builder setRadius(int radius) {
            this.radius = radius;
            return this;
        }
        @Override
        public Builder setCenter(Pos center) {
            this.center = center;
            return this;
        }
        @Override
        public Builder setRelation(Relation relation) {
            this.relation = relation;
            return this;
        }
        @Override
        public Builder setTags(TagContainer tags) {
            this.tags = tags;
            return this;
        }
        @Override
        public Query.Builder setTags(Consumer<TagContainer.Builder> builderConsumer) {
            TagContainer.Builder builder = tags.toBuilder();
            builderConsumer.accept(builder);
            tags = builder.build();
            return this;
        }

        @Override
        public Query build() {
            return new QueryImpl(this);
        }
    }
}
