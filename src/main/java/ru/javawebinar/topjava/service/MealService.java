package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    @Autowired
    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    public void delete(int id, int userid) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userid), id);
    }

    public Meal get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public List<Meal> getAll(Integer userId) {
        if (userId != null) {
            return checkNotFoundWithId(repository.getAll(userId), userId);
        }
        return new ArrayList<>();
    }

    public List<Meal> getFilteredByDate(int userId, LocalDate startDate, LocalDate endDate) {
        return checkNotFoundWithId(repository.getFilteredByDate(userId, startDate, endDate), userId);
    }

    public void update(Meal meal) throws NotFoundException {
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }

}