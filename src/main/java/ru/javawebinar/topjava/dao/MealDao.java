package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    Meal saveOrUpdate(Meal meal);

    void delete(Integer id);

    Meal getById(Integer id);

    List<Meal> getAll();
}
