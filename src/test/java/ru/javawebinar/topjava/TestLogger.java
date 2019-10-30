package ru.javawebinar.topjava;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import java.util.concurrent.TimeUnit;

public class TestLogger extends Stopwatch {
    //private static final Logger log = LoggerFactory.getLogger(MealTestData.class);

    public static StringBuffer testInfo = new StringBuffer(String
            .format("%-20s| %s ms %n", "Test Name", "Execution Time"));

    private static void logInfo(Description description, long nanos) {
        String testName = description.getMethodName();
        testInfo.append(String.format("%-20s| %s ms %n", testName, TimeUnit.NANOSECONDS.toMillis(nanos)));
        //log.debug("{}: {} ms", testName, TimeUnit.NANOSECONDS.toMillis(nanos));
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, nanos);
    }
}
