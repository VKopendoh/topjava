package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealInMemoryDao implements MealDao {
    private final AtomicInteger id = new AtomicInteger();
    private Map<Integer, Meal> meals = new ConcurrentHashMap<>();

    @Override
    public Meal saveOrUpdate(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(id.incrementAndGet());
        }
            meals.put(meal.getId(), meal);

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
