package com.tests.lab.collections.api;

public interface List<E> extends Collection<E> {

    boolean add(E element);

    boolean add(Collection<? extends E> elements);

    boolean remove(E element);

    boolean remove(Collection<? extends E> elements);

    int size();

    int contains();
}
