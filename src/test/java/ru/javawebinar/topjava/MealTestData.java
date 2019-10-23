package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;


public class MealTestData {

    public static final int USER_MEAL_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = USER_MEAL_ID + 6;


    public static final Meal USER_MEAL_ONE = new Meal(USER_MEAL_ID, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
    public static final Meal USER_MEAL_TWO = new Meal(USER_MEAL_ID + 1, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
    public static final Meal USER_MEAL_THREE = new Meal(USER_MEAL_ID + 2, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal USER_MEAL_FOUR = new Meal(USER_MEAL_ID + 3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal USER_MEAL_FIVE = new Meal(USER_MEAL_ID + 4, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal USER_MEAL_SIX = new Meal(USER_MEAL_ID + 5, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);

    public static final Meal ADMIN_MEAL_ONE = new Meal(ADMIN_MEAL_ID,
            LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);
    public static final Meal ADMIN_MEAL_TWO = new Meal(ADMIN_MEAL_ID + 1, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);


    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
