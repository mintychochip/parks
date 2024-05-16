package org.example;

import java.util.HashMap;
import java.util.Map;

final class TagContainerImpl implements TagContainer {
    private final Map<String, String> tags;

    TagContainerImpl() {
        tags = new HashMap<>();
    }

    TagContainerImpl(Builder builder) {
        tags = builder.tags;
    }

    @Override
    public Map<String, String> getTags() {
        return tags;
    }

    @Override
    public TagContainer.Builder toBuilder() {
        return new Builder(this);
    }

    static final class Builder implements TagContainer.Builder {

        private Map<String,String> tags;
        Builder() {
            tags = new HashMap<>();
        }

        Builder(TagContainerImpl tagContainer) {
            tags = tagContainer.tags;
        }
        @Override
        public TagContainer build() {
            return new TagContainerImpl(this);
        }

        @Override
        public TagContainer.Builder addTag(String key, String val) {
            if(tags == null) {
                tags = new HashMap<>();
            }
            tags.put(key,val);
            return this;
        }
    }
}
