package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static MealDao meals = MealsUtil.generate();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.debug("IN doPOST");
        request.setCharacterEncoding("UTF-8");
        String desc = request.getParameter("description");
        int cals = Integer.parseInt(request.getParameter("calories"));
        LocalDateTime ldt = LocalDateTime.parse(request.getParameter("dateTime"));

        Meal meal;
        if (!request.getParameter("id").isEmpty()) {
            int id = Integer.parseInt(request.getParameter("id"));
            meal = new Meal(ldt, desc, cals, id);
        } else {
            meal = new Meal(ldt, desc, cals, meals.generateId());
        }

        meals.saveOrUpdate(meal);
        log.debug("=====> doPost successful done");
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<MealTo> mealsWithExceed = MealsUtil.getFiltered(meals.getAll(), LocalDateTime.MIN.toLocalTime(),
                LocalDateTime.MAX.toLocalTime(), MealsUtil.DEFAULT_CALORIES_PER_DAY);

        log.debug("IN doGET");
        String action = request.getParameter("action");
        if (action == null) {
            System.out.println("REDIRECT TO LIST");
            request.setAttribute("mealsWithExceed", mealsWithExceed);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
            return;
        }
        switch (action) {
            case "delete":
                meals.delete(Integer.valueOf(request.getParameter("id")));
                response.sendRedirect("meals");
                break;
            case "update":
                log.debug("===> UPDATE" + request.getParameter("id"));
                request.setAttribute("meal", meals.getById(Integer
                        .parseInt(request.getParameter("id"))));
                request.setAttribute("mealsWithExceed", mealsWithExceed);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }
}
