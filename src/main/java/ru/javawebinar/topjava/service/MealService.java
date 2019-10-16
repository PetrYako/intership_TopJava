package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;


import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.repository = mealRepository;
    }

    public Meal create(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    public void delete(int userId, int id) {
        checkNotFoundWithId(repository.delete(userId, id), userId);
    }

    public Meal get(int userId, int id) {
        return checkNotFoundWithId(repository.getMeal(userId, id), userId);
    }

    public List<Meal> getAll() { return (List<Meal>) repository.getAll(); }

    public List<Meal> getFilteredByDate(int userId, LocalDateTime startTime, LocalDateTime endTime) {
        return repository.getMealFilteredByDate(userId, startTime, endTime);
    }

    public void update(int userId, Meal meal) {
        checkNotFoundWithId(repository.save(userId, meal), userId);
    }
}