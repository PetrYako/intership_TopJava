package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealDao mealDao = new MealDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String MEAL_LIST = "/meals.jsp";
        String ADD_UPDATE = "/addUpdate.jsp";
        String path = "";

        if (action == null) {
            path = MEAL_LIST;
            request.setAttribute("meals", mealDao.getAll());
        } else if (action.equalsIgnoreCase("delete")) {
            path = MEAL_LIST;
            mealDao.delete(Integer.parseInt(request.getParameter("mealId")));
            request.setAttribute("meals", mealDao.getAll());
        } else if (action.equalsIgnoreCase("edit")) {
            path = ADD_UPDATE;
            int id = Integer.parseInt(request.getParameter("mealId"));
            MealTo mealTo = mealDao.get(id);
            request.setAttribute("date", mealTo.getDateTime());
            request.setAttribute("description", mealTo.getDescription());
            request.setAttribute("calories", mealTo.getCalories());
            } else if (action.equalsIgnoreCase("create")) {
            path = ADD_UPDATE;
        }

        log.debug("redirect to " + path);
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = req.getParameter("mealId") == null ? 0 : Integer.parseInt(req.getParameter("mealId"));
        LocalDateTime date = LocalDateTime.parse(req.getParameter("date"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        MealTo mealTo;
        if (id == 0)
            mealTo = new MealTo(date, description, calories, calories > MealsUtil.DEFAULT_CALORIES_PER_DAY);
        else {
            mealDao.delete(id);
            mealTo = new MealTo(date, description, calories, calories > MealsUtil.DEFAULT_CALORIES_PER_DAY);
            mealTo.setId(id);
        }
        mealDao.add(mealTo);

        req.setAttribute("meals", mealDao.getAll());
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
