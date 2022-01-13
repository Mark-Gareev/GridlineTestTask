package com.gridnine.testing;

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BeforeCurrentTimeFilterTest {
    private static FlightFilter flightFilter;
    private static Flight testFlight;
    private static Method createFlight;//adding from TestClasses with reflection for testing only;

    @BeforeClass
    public static void initialize() {
        Class<FlightBuilder> clazz = FlightBuilder.class;
        try {
            createFlight = clazz.getDeclaredMethod("createFlight", LocalDateTime[].class);
            createFlight.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        flightFilter = new BeforeCurrentTimeFilter();
    }

    @Test
    public void machWithFlightDepartsNow() {
        try {
            testFlight = (Flight) createFlight.invoke(null,
                    (Object) new LocalDateTime[]{LocalDateTime.now(), LocalDateTime.now().plusHours(1)});
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertFalse("match with departs now,expected false",
                flightFilter.match(testFlight));
    }

    @Test
    public void matchWithFlightDepartsBeforeNow() {
        try {
            testFlight = (Flight) createFlight.invoke(null,
                    (Object) new LocalDateTime[]{LocalDateTime.now().minusDays(1), LocalDateTime.now().plusHours(1)});
            assertFalse("match with departs before now,expected false",
                    flightFilter.match(testFlight));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void matchWithFlightDepartsAfterNow() {
        try {
            testFlight = (Flight) createFlight.invoke(null,
                    (Object) new LocalDateTime[]{LocalDateTime.now().plusDays(1),
                            LocalDateTime.now().plusDays(1).plusHours(1)});
            assertTrue("match with departs after now,expected true", flightFilter.match(testFlight));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void matchWithNull() {
        assertFalse("match with null expect false", flightFilter.match(null));
    }

    @Test
    public void matchManyTimes() {
        try {
            testFlight = (Flight) createFlight.invoke(null,
                    (Object) new LocalDateTime[]{LocalDateTime.now().plusDays(1),
                            LocalDateTime.now().plusDays(1).plusHours(1)});
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            flightFilter.match(testFlight);
        }
        long finishTime = System.currentTimeMillis();
        assertTrue("average time expected less 1us",
                1 > (double) (finishTime - startTime) / 500);

    }
}