package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    int deleteMealByIdAndUserId(int id, int userId);

    Meal findByIdAndUserId(int id, int userId);

    List<Meal> findAllByUserIdAndDateTimeGreaterThanAndDateTimeLessThanOrderByDateTimeDesc(int userid,
                                                                                           LocalDateTime startDate,
                                                                                           LocalDateTime endDate);

    List<Meal> findAllByUserIdOrderByDateTimeDesc(int userid);
}
