package com.thescore.proficiencyexercise.widgets.recycler.adapter;

import java.util.Collection;

public interface RecyclerList<T> {

    void addItem(T item);

    void addItem(Collection<T> items);

    void addItem(T... items);

    boolean remove(T item);

    T removeAt(int index);

    void updateAt(int index, T item);

    T get(int index);

    int indexOf(T item);

    void clear();

    int listSize();
}
