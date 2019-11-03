package ru.javawebinar.topjava;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class LoggerUtils extends Stopwatch {

    private static final Logger log = getLogger("result");

    private static StringBuilder results = new StringBuilder();

    private static void logInfo(Description description, long nanos) {
        String result = String.format("\n%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
        results.append(result);
        log.info(result + " ms\n");
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, nanos);
    }


    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }


}
