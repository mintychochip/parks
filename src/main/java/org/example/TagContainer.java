package org.example;

import java.util.Map;

public interface TagContainer extends Buildable<TagContainer, TagContainer.Builder>{

    static TagContainer create() {
        return new TagContainerImpl();
    }
    static Builder builder() {
        return new TagContainerImpl.Builder();
    }
    Map<String,String> getTags();

    interface Builder extends AbstractBuilder<TagContainer> {
        Builder addTag(String key, String val);
    }
}
