package ru.javawebinar.topjava.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.javawebinar.topjava.service.datajpa.DataJpaMealService;
import ru.javawebinar.topjava.service.datajpa.DataJpaUserService;
import ru.javawebinar.topjava.service.jdbc.JdbcMealService;
import ru.javawebinar.topjava.service.jdbc.JdbcUserService;
import ru.javawebinar.topjava.service.jpa.JpaMealService;
import ru.javawebinar.topjava.service.jpa.JpaUserService;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        JdbcUserService.class,
        JpaUserService.class,
        DataJpaUserService.class,
        JdbcMealService.class,
        JpaMealService.class,
        DataJpaMealService.class
})

public class AllTests {
}
