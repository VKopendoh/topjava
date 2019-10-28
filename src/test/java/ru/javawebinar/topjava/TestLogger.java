package ru.javawebinar.topjava;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestLogger extends Stopwatch {

    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);
    public static List<String> testInfo;

    private static void logInfo(Description description, String status, long nanos) {
        if (testInfo == null) {
            testInfo = new ArrayList<>();
        }
        String testName = description.getMethodName();
        String info = String.format("Test %s %s, spent %d microseconds",
                testName, status, TimeUnit.NANOSECONDS.toMicros(nanos));
        if ("succeeded".equals(status) || status.contains("throws")) {
            testInfo.add(info);
        }
        log.debug(info);
    }

    @Override
    protected void succeeded(long nanos, Description description) {
        logInfo(description, "succeeded", nanos);
    }

    @Override
    protected void failed(long nanos, Throwable e, Description description) {
        logInfo(description, "throws" + e.getClass().getName(), nanos);
    }

    @Override
    protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
        logInfo(description, "skipped", nanos);
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, "finished", nanos);
    }
}
