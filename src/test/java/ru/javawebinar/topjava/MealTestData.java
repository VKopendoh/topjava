package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;


public class MealTestData {

    public static final int USER_MEAL_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = USER_MEAL_ID + 6;


    public static List<Meal> USER_MEALS = Arrays.asList(
            new Meal(USER_MEAL_ID, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
            new Meal(USER_MEAL_ID + 1, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(USER_MEAL_ID + 2, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(USER_MEAL_ID + 3, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(USER_MEAL_ID + 4, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(USER_MEAL_ID + 5, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500)
    );
    public static final List<Meal> ADMIN_MEALS = Arrays.asList(
            new Meal(ADMIN_MEAL_ID, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500),
            new Meal(ADMIN_MEAL_ID + 1, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510)

    );

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "id");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
