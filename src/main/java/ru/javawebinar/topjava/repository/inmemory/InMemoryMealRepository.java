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
    private Map<Integer, List<Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach((m) -> save(0, m));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (!repository.containsKey(userId)) return null;

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.get(userId).add(meal);
            return meal;
        }
        // treat case: update, but not present in storage
        return repository.get(userId).set(meal.getId(), meal);
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
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        Collection<Meal> meals  = new ArrayList<>();
        repository.forEach((id, list) -> meals.addAll(list));
        return meals.stream()
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getMealFilteredByDate(int userId, LocalDateTime startTime, LocalDateTime endTime) {
        return repository.get(userId).stream()
                .filter((m) -> DateTimeUtil.isBetweenTime(m.getDateTime(), startTime, endTime))
                .collect(Collectors.toList());
    }
}

