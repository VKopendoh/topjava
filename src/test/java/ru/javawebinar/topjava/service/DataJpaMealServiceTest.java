package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealServiceTest extends MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void getMealWithUser() throws Exception {
        Meal meal = service.getMealWithUser(MEAL1_ID, USER_ID);
        User user = meal.getUser();
        assertMatch(meal, MEAL1);
        UserTestData.assertMatch(user, USER);
    }
}
