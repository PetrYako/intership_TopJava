package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({"classpath:spring/spring-app.xml",
                        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        assertMatch(service.get(MEAL_1, USER_ID), meal1);
    }

    @Test(expected = NotFoundException.class)
    public void getAlien() {
        assertMatch(service.get(MEAL_1, ADMIN_ID), meal1);
    }

    @Test
    public void delete() {
        service.delete(MEAL_1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteAlien() {
        service.delete(MEAL_1, ADMIN_ID);
        assertMatch(service.getAll(USER_ID), meal1);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> expected = Arrays.asList(meal2, meal3);
        assertMatch(service.getBetweenDates(LocalDate.of(2019, 10, 22), null, USER_ID), meal1, meal2, meal3);
    }

    @Test
    public void getAll() {
        List<Meal> expected = Arrays.asList(meal3, meal2, meal1);
        assertMatch(service.getAll(USER_ID), expected);
    }

    @Test
    public void update() {
        Meal updated = new Meal(meal1);
        updated.setCalories(200);
        service.update(updated, USER_ID);
        assertMatch(service.get(updated.getId(), USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateAlien() {
        Meal updated = new Meal(meal1);
        updated.setCalories(200);
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(updated.getId(), ADMIN_ID), updated);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(null, LocalDateTime.of(2019, 10, 18, 12, 00), "Lunch", 200);
        Meal createdMeal = service.create(newMeal, USER_ID);
        newMeal.setId(createdMeal.getId());
        assertMatch(service.getAll(USER_ID), meal3, meal2, meal1, createdMeal);
    }
}