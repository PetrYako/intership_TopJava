package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static ru.javawebinar.topjava.util.DateTimeUtil.getStartInclusive;
import static ru.javawebinar.topjava.util.DateTimeUtil.getEndExclusive;

@Repository
public class DataJpaMealRepository implements MealRepository {

    @Autowired
    private CrudMealRepository crudRepository;

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setUser(crudRepository.getUser(userId));
            return crudRepository.save(meal);
        }
        return crudRepository.update(meal.getDateTime(), meal.getDescription(), meal.getCalories(), userId, meal.getId()) == 0 ? null : meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.get(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getBetweenInclusive(null, null, userId);
    }

    @Override
    public List<Meal> getBetweenInclusive(LocalDate startDate, LocalDate endDate, int userId) {
        return crudRepository.findByDate(getStartInclusive(startDate), getEndExclusive(endDate), userId);
    }
}
