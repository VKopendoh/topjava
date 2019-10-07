package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MealInMemoryDao implements MealDao {
    private List<Meal> meals;

    private static final MealInMemoryDao INSTANCE = new MealInMemoryDao();

    private MealInMemoryDao() {
        meals = new CopyOnWriteArrayList<>();
    }

    public static MealInMemoryDao get() {
        return INSTANCE;
    }

    @Override
    public void saveOrUpdate(Meal meal) {
        Meal mealInDao = getById(meal.getId());
        if (mealInDao == null) {
            meals.add(meal);
        } else {
            meals.set(meals.indexOf(mealInDao), meal);
        }
    }

    @Override
    public void delete(int id) {
        meals.forEach(meal -> {
            if (meal.getId() == id) {
                meals.remove(meal);
            }
        });
    }

    @Override
    public Meal getById(int id) {
        return meals.stream()
                .filter(meal -> id == meal.getId())
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Meal> getAll() {
        return meals;
    }

    @Override
    public int generateId() {
        return meals.stream().mapToInt(Meal::getId).max().orElse(-1) + 1;
    }
}
