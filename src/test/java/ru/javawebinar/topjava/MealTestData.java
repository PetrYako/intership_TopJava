package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final int MEAL_1 = START_SEQ;
    public static final int MEAL_2 = START_SEQ + 1;
    public static final int MEAL_3 = START_SEQ + 2;

    public static final Meal meal1 = new Meal(MEAL_1, LocalDateTime.of(2019, 10, 23, 9, 00), "Breakfast", 300);
    public static final Meal meal2 = new Meal(MEAL_2, LocalDateTime.of(2019, 10, 23, 12, 00), "Lunch", 500);
    public static final Meal meal3 = new Meal(MEAL_3, LocalDateTime.of(2019, 10, 23, 19, 00), "Dinner", 500);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
