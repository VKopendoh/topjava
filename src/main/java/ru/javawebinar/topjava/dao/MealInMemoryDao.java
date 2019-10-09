package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MealInMemoryDao implements MealDao {

    private Map<Integer, Meal> meals = new ConcurrentHashMap<>();

    @Override
    public Meal saveOrUpdate(Meal meal) {
        Lock lock = new ReentrantLock();
        if (meal.getId() == null) {
            meal.setId(generateId());
        }
        Meal mealInDao = getById(meal.getId());
        lock.lock();
        if (mealInDao == null) {
            meals.put(meal.getId(), meal);
        } else {
            meals.put(mealInDao.getId(), meal);
        }
        lock.unlock();
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

    private Integer generateId() {
        return UUID.randomUUID().hashCode();
    }
}
