package com.gridnine.testing;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlightFilterServiceTest {
    private static FlightFilterService flightFilterService;
    private static List<Flight> testFlights;


    @BeforeClass
    public static void initialize() {
        flightFilterService = new FlightFilterService();
        testFlights = FlightBuilder.createFlights();
        flightFilterService.setFlightsInUse(testFlights);
    }

    @Test
    public void runningWithNothingSetUp() {
        flightFilterService.setFlightsInUse(Collections.emptyList());
        flightFilterService.setFiltersInUse(Collections.emptyList());
        Assert.assertEquals("nothing set up expected empty list",
                Collections.EMPTY_LIST, flightFilterService.getFilteredFlights());
    }

    @Test
    public void runningWithNoFlights() {
        flightFilterService.setFlightsInUse(Collections.emptyList());
        flightFilterService.setFiltersInUse(Arrays.asList(new BeforeCurrentTimeFilter()));
        Assert.assertEquals("no flights set up expected empty list",
                Collections.EMPTY_LIST, flightFilterService.getFilteredFlights());
    }

    @Test
    public void runningWithOneFilter() {
        flightFilterService.setFlightsInUse(testFlights);
        flightFilterService.setFiltersInUse(Arrays.asList(new BeforeCurrentTimeFilter()));
        Assert.assertFalse("run with one filter expect not empty list",
                flightFilterService.getFilteredFlights().isEmpty());
    }

    @Test
    public void runningWithTwoFilters() {
        flightFilterService.setFlightsInUse(testFlights);
        flightFilterService.setFiltersInUse(Arrays.asList(new BeforeCurrentTimeFilter(),
                new TimeBetweenSegmentsFilter()));
        Assert.assertFalse("run with two filters expect not empty list",
                flightFilterService.getFilteredFlights().isEmpty());
    }

    @Test
    public void runningWithThreeFilters() {
        flightFilterService.setFlightsInUse(testFlights);
        flightFilterService.setFiltersInUse(Arrays.asList(new BeforeCurrentTimeFilter(),
                new TimeBetweenSegmentsFilter(),
                new SegmentStartsAfterFinishesFilter()));
        Assert.assertFalse("run with three filters expect not empty list",
                flightFilterService.getFilteredFlights().isEmpty());
    }

    @Test
    public void runningWithNoFilters() {
        flightFilterService.setFlightsInUse(testFlights);
        flightFilterService.setFiltersInUse(Collections.emptyList());
        Assert.assertEquals("run with no filters expect source List",
                flightFilterService.getFilteredFlights(), testFlights);
    }
    @Test
    public void runningWithNull(){
        flightFilterService.setFlightsInUse(null);
        flightFilterService.setFiltersInUse(null);
        Assert.assertTrue("running with null expect empty list", flightFilterService.getFilteredFlights().isEmpty());
    }

}
