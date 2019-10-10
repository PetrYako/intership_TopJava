package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MealDao implements Dao<MealTo> {

    private Map<Integer, MealTo> mealMap;

    public MealDao() {
        mealMap = init();
    }

    @Override
    public Collection<MealTo> getAll() {
        return mealMap.values();
    }

    private ConcurrentHashMap<Integer, MealTo> init() {
        ConcurrentHashMap<Integer, MealTo> mealToMap = new ConcurrentHashMap<>();
        List<MealTo> list = MealsUtil.getMealWithoutFilter(Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Breakfast", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Lunch", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Dinner", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Breakfast", 2001),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Lunch", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Dinner", 510)
        ), MealsUtil.DEFAULT_CALORIES_PER_DAY);

        list.forEach(mealTo -> mealToMap.put(mealTo.getId(), mealTo));
        return mealToMap;
    }

    @Override
    public void add(MealTo mealTo) {
        mealMap.put(mealTo.getId(), mealTo);
    }

    @Override
    public void delete(int id) {
        mealMap.remove(id);
    }

    @Override
    public MealTo get(int id) {
      return mealMap.get(id);
    }
}
