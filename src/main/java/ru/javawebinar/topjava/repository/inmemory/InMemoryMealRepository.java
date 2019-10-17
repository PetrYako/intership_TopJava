package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(1, meal));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (!repository.containsKey(userId)) {
            repository.put(userId, new HashMap<>());
        }

        if (meal.isNew()) {
            meal.setId(counter.getAndIncrement());
            repository.get(userId).put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but not present in storage
        return repository.get(userId).computeIfPresent(meal.getId(), (integer, meal1) -> meal);
    }

    @Override
    public boolean delete(int userId, int id) {
        if (!repository.containsKey(userId)) return false;
        return repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal getMeal(int userId, int id) {
        if (!repository.containsKey(userId)) return null;
        return repository.get(userId).get(id);
    }

    @Override
    public List<Meal> getUserMeal(int id) {
        return (List<Meal>) repository.get(id).values();
    }

    @Override
    public Collection<Meal> getAll() {
        Collection<Meal> meals  = new ArrayList<>();
        repository.forEach((id, map) -> meals.addAll(map.values()));
        return meals.stream()
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getMealFilteredByDate(int userId, LocalDateTime startTime, LocalDateTime endTime) {
        return getAll().stream()
                .filter((m) -> DateTimeUtil.isBetweenTime(m.getDateTime(), startTime, endTime))
                .collect(Collectors.toList());
    }
}

