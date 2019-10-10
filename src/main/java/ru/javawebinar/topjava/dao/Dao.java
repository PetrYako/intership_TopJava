package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.MealTo;

import java.util.Collection;

public interface Dao<T> {

    Collection<MealTo> getAll();

    void add(T t);

    void delete(int id);

    MealTo get(int id);
}
