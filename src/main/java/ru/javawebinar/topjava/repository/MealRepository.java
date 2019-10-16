package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface MealRepository {
    // null if not found, when updated
    Meal save(int userId, Meal meal);

    // false if not found
    boolean delete(int userId, int id);

    // null if not found
    Meal getMeal(int userId, int id);

    List<Meal> getUserMeal(int id);

    Collection<Meal> getAll();

    List<Meal> getMealFilteredByDate(int userId, LocalDateTime startTime, LocalDateTime endTime);
}
