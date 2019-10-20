package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            log.debug("new meal with id" + meal.getId());
            if (!repository.containsKey(meal.getUserId())) {
                Map<Integer, Meal> meals = new ConcurrentHashMap<>();
                addMeal(meal,meals);
            } else {
                Map<Integer, Meal> meals = repository.get(meal.getUserId());
                addMeal(meal, meals);
            }
            return meal;
        }
        // treat case: update, but not present in storage
        Map<Integer, Meal> meals = repository.get(meal.getUserId());
        return meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(Integer userId, int id) {

        if (repository.get(userId).get(id) != null &&
                userId.equals(repository.get(userId).get(id).getUserId())) {
            return repository.get(userId).remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(Integer userId, int id) {
        if (repository.containsKey(userId)) {
            if (repository.get(userId).containsKey(id)) {
                return repository.get(userId).get(id);
            }
        }
        return null;
    }

    @Override
    public List<Meal> getAll(Integer userId) {
        return repository.get(userId)
                .values()
                .stream()
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getFilteredByDate(Integer userId, LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        return getAll(userId).stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());

    }

    private void addMeal(Meal meal, Map<Integer, Meal> meals) {
        meals.put(meal.getId(), meal);
        repository.put(meal.getUserId(), meals);
    }
}

