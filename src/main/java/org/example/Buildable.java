package org.example;

public interface Buildable<T,V extends AbstractBuilder<T>> {
    V toBuilder();
}
