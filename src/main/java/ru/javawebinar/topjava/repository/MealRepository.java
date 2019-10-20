package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository {
    // null if not found, when updated
    Meal save(Meal meal);

    // false if not found
    boolean delete(Integer userId, int id);

    // null if not found
    Meal get(Integer userId, int id);

    List<Meal> getAll(Integer userId);

    List<Meal> getFilteredByDate(Integer userId, LocalDate startDate, LocalDate endDate);

}
