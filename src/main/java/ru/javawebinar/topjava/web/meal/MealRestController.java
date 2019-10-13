package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal create(Meal meal) {
        log.info("create {}", meal.getId());
        return service.create(meal);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, SecurityUtil.authUserId());
    }

    public void update(Meal meal) {
        log.info("update {}", meal.getId());
        service.update(meal);
    }

    private List<MealTo> getAll() {
        log.info("getAll");
        return MealsUtil.getTos(service.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, SecurityUtil.authUserId());
    }

    public List<MealTo> getAllFiltered(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        if (startDate != null && endDate != null && startTime != null && endTime != null) {
            return MealsUtil.getFilteredTos(service.getFilteredByDate(SecurityUtil.authUserId(),
                    startDate, endDate), SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);
        } else if (startDate != null && endDate != null) {
            return MealsUtil.getTos(service.getFilteredByDate(SecurityUtil.authUserId(),
                    startDate, endDate), SecurityUtil.authUserCaloriesPerDay());
        } else if (startTime != null && endTime != null) {
            return MealsUtil.getFilteredTos(service.getAll(SecurityUtil.authUserId())
                    , SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);
        } else {
            return getAll();
        }

    }
}