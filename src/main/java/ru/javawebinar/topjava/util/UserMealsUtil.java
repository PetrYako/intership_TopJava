package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> userMealWithExceeds = new ArrayList<>();
        for (UserMeal meal : mealList) {
            LocalTime mealTime = meal.getDateTime().toLocalTime();
            if (TimeUtil.isBetween(mealTime, startTime, endTime)) {
                if (meal.getCalories() <= caloriesPerDay)
                    userMealWithExceeds.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), false));
                else
                    userMealWithExceeds.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), true));
            }
        }
        return userMealWithExceeds;
    }

    public static List<UserMealWithExceed> getFilteredWithExceedByStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return mealList.stream()
                .filter(m -> TimeUtil.isBetween(m.getDateTime().toLocalTime(), startTime, endTime))
                .map(m -> {
                    if (m.getCalories() <= caloriesPerDay)
                        return new UserMealWithExceed(m.getDateTime(), m.getDescription(), m.getCalories(), false);
                    else
                        return new UserMealWithExceed(m.getDateTime(), m.getDescription(), m.getCalories(), true);
                })
                .collect(Collectors.toList());
    }

}
