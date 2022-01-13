package com.gridnine.testing;

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TimeBetweenSegmentsFilterTest {

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
        flightFilter = new TimeBetweenSegmentsFilter();
    }

    @Test
    public void matchWithTimeBetweenMoreTwoHours() {
        try {
            testFlight = (Flight) createFlight.invoke(null,
                    (Object) new LocalDateTime[]{LocalDateTime.now(), LocalDateTime.now().minusHours(1),
            LocalDateTime.now().plusHours(4),LocalDateTime.now().plusHours(7)});
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        assertFalse("match with time between segments more than two hours,expected false",
                flightFilter.match(testFlight));
    }

    @Test
    public void matchWithFlightWithTimeBetweenSegmentsEqualsTwoHours() {
        try {
            testFlight = (Flight) createFlight.invoke(null,
                    (Object) new LocalDateTime[]{LocalDateTime.now().minusDays(1), LocalDateTime.now().plusHours(1),
                    LocalDateTime.now().plusHours(3),LocalDateTime.now().plusHours(5)});
            assertTrue("match with time equals two hours, expect true",
                    flightFilter.match(testFlight));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void matchWithFlightTimesLessTwoHours() {
        try {
            testFlight = (Flight) createFlight.invoke(null,
                    (Object) new LocalDateTime[]{LocalDateTime.now().plusDays(1),
                            LocalDateTime.now().plusDays(1).plusHours(1),
            LocalDateTime.now().plusDays(1).plusHours(2),LocalDateTime.now().plusDays(1).plusHours(3)});
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
    public void matchManyTimes(){
        try {
            testFlight = (Flight) createFlight.invoke(null,
                    (Object) new LocalDateTime[]{LocalDateTime.now().plusDays(1),
                            LocalDateTime.now().plusDays(1).plusHours(1),
                            LocalDateTime.now().plusDays(1).plusHours(2),LocalDateTime.now().plusDays(1).plusHours(3)});
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 500000;i++){
            flightFilter.match(testFlight);
        }
        long finishTime = System.currentTimeMillis();

        assertTrue("average time expected less 1us",
                1>(double)(finishTime-startTime)/500);

    }
}