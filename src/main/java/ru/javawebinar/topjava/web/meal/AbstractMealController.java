package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

public abstract class AbstractMealController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public MealService service;

    public List<Meal> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Meal get(int userId, int id) {
        log.info("get {}", id);
        return service.get(userId, id);
    }

    public Meal create(int userId, Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(userId, meal);
    }

    public void delete(int userId, int id) {
        log.info("delete {}", id);
        service.delete(userId, id);
    }

    public void update(int userId, Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(userId, meal);
    }

    public List<Meal> getFilteredByDate(int userId, LocalDateTime startTime, LocalDateTime endTime) {
        log.info("getFilteredByDate");
        return service.getFilteredByDate(userId, startTime, endTime);
    }
}
