package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Controller
public class JspMealController {

    @Autowired
    private MealRestController mealRestController;

    @GetMapping("meals")
    public String getMeals(Model model) {
        model.addAttribute("meals", mealRestController.getAll());
        return "meals";
    }

    @GetMapping("meals/delete/{id}")
    public String deleteMeal(@PathVariable("id") int id) {
        mealRestController.delete(id);
        return "redirect:/meals";
    }

    @GetMapping("meals/update/{id}")
    public String updateRequest(@PathVariable("id") int id, Model model) {
        Meal meal = mealRestController.get(id);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping("meals/create")
    public String createRequest(Model model) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping("meals/create")
    public String createMeal(HttpServletRequest request) {
        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
                            request.getParameter("description"),
                            Integer.parseInt(request.getParameter("calories")));

        mealRestController.create(meal);
        return "redirect:/meals";
    }

    @PostMapping("meals/update/{id}")
    public String updateMeal(HttpServletRequest request, @PathVariable("id") int id) {
        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        mealRestController.update(meal, id);
        return "redirect:/meals";
    }
}
