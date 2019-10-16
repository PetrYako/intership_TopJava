package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class MealRestController extends AbstractMealController{
    private MealService service;


    @Override
    public List<Meal> getAll() {
        return super.getAll();
    }

    @Override
    public Meal get(int userId, int id) {
        return super.get(userId, id);
    }

    @Override
    public Meal create(int userId, Meal meal) {
        return super.create(userId, meal);
    }

    @Override
    public void delete(int userId, int id) {
        super.delete(userId, id);
    }

    @Override
    public void update(int userId, Meal meal, int id) {
        super.update(userId, meal, id);
    }

    @Override
    public List<Meal> getFilteredByDate(int userId, LocalDateTime startTime, LocalDateTime endTime) {
        return super.getFilteredByDate(userId, startTime, endTime);
    }
}