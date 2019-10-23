package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(USER_MEAL_ID, USER_ID);
        assertMatch(meal, USER_MEAL_ONE);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(USER_MEAL_ID, ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(ADMIN_MEAL_ID, ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), ADMIN_MEAL_TWO);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(USER_MEAL_ID, ADMIN_ID);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> meals = service
                .getBetweenDates(LocalDate.of(2015, Month.MAY, 31), null, USER_ID);
        assertMatch(meals, Arrays.asList(USER_MEAL_ONE, USER_MEAL_TWO, USER_MEAL_THREE));
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(ADMIN_ID);

        assertMatch(all, Arrays.asList(ADMIN_MEAL_ONE, ADMIN_MEAL_TWO));
    }

    @Test
    public void update() {
        Meal updated = new Meal(USER_MEAL_ONE);
        updated.setDescription("new breakfast");
        updated.setCalories(1500);
        service.update(updated, USER_ID);
        assertMatch(service.get(USER_MEAL_ID, USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        service.update(service.get(USER_MEAL_ID, USER_ID), ADMIN_ID);
    }

    @Test(expected = DuplicateKeyException.class)
    public void updateDuplicateMeal() throws Exception {
        Meal duplicate = new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Dinner", 1510);
        service.create(duplicate, USER_ID);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.now(), "branch", 1000);
        Meal created = service.create(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(ADMIN_ID), newMeal, ADMIN_MEAL_ONE, ADMIN_MEAL_TWO);
    }

}