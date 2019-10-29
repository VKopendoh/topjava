package ru.javawebinar.topjava;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestLogger extends Stopwatch {

    private static final Logger log = LoggerFactory.getLogger(TestLogger.class);
    public static Map<String, String> testInfo;

    private static void logInfo(Description description, long nanos) {
        if (testInfo == null) {
            testInfo = new HashMap<>();
        }
        String testName = description.getMethodName();
        testInfo.put(testName, String.valueOf(TimeUnit.NANOSECONDS.toMillis(nanos)));
        log.debug("{}: {} ms", testName, TimeUnit.NANOSECONDS.toMillis(nanos));
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, nanos);
    }
}
