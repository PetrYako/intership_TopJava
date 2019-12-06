package ru.javawebinar.topjava.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/ajax/meals/")
public class MealAjaxController extends AbstractMealController {

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealTo> getAll() {
        return super.getAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(@RequestParam Integer id,
                               @RequestParam String description,
                               @RequestParam Integer calories,
                               @RequestParam LocalDateTime localDateTime) {

        Meal meal = new Meal(id, localDateTime, description, calories);
        if (meal.isNew()) {
            super.create(meal);
        }
    }
}
//    @Override
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<MealTo> getBetween(@RequestParam LocalDate startDate,
//                                   @RequestParam LocalTime startTime,
//                                   @RequestParam LocalDate endDate,
//                                   @RequestParam LocalTime endTime) {
//
//        return super.getBetween(startDate, startTime, endDate, endTime);
//    }
//}
