package com.tests.lab.collections.api;

public interface Collection<E> {
    boolean add(E element);

    boolean add(Collection<? extends E> elements);

    boolean remove(E element);

    boolean remove(Collection<? extends E> elements);

    int size();

    int contains();
}
