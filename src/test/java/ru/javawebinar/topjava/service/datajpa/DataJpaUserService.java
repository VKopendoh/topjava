package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserService;
import ru.javawebinar.topjava.service.UserService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserService extends AbstractUserService {
    @Autowired
    UserService service;

    @Test
    public void getMeals() throws Exception {
        User user = service.getWithMeals(USER_ID);
        List<Meal> meals = user.getMeals();
        assertMatch(meals, MEALS);
    }

    @Test
    public void getEmptyMeals() {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", 1555, false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        assertThat(service.getWithMeals(created.getId()).getMeals().size()).isEqualTo(0);
    }
}
