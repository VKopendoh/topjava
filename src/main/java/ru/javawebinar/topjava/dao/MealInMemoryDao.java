package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MealInMemoryDao implements MealDao {

    private ConcurrentHashMap<Integer, Meal> meals = new ConcurrentHashMap<>();

    @Override
    public Meal saveOrUpdate(Meal meal) {
        Meal mealInDao = getById(meal.getId());
        if (mealInDao == null) {
            meals.put(meal.getId(), meal);
        } else {
            meals.put(mealInDao.getId(), meal);
        }
        return meal;
    }

    @Override
    public void delete(Integer id) {
        meals.remove(id);
    }

    @Override
    public Meal getById(Integer id) {
        return meals.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new CopyOnWriteArrayList<>(meals.values());
    }
}
