package ru.javawebinar.topjava.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        JdbcUserServiceTest.class,
        JpaUserServiceTest.class,
        DataJpaUserServiceTest.class,
        JdbcMealServiceTest.class,
        JpaMealServiceTest.class,
        DataJpaMealServiceTest.class
})

public class AllTests {
}
