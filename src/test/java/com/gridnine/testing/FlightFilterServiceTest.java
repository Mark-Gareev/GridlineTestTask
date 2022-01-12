package com.gridnine.testing;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlightFilterServiceTest {
    private static FlightFilterService service;
    private static List<Flight> testFlights;


    @BeforeClass
    public static void initialize() {
        service = new FlightFilterService();
        testFlights = FlightBuilder.createFlights();
        service.setFlightsInUse(testFlights);
    }

    @Test
    public void runningWithNothingSetUp() {
        service.setFlightsInUse(Collections.emptyList());
        service.setFiltersInUse(Collections.emptyList());
        Assert.assertEquals("nothing set up expected empty list",
                Collections.EMPTY_LIST, service.getFilteredFlights());
    }

    @Test
    public void runningWithNoFlights() {
        service.setFlightsInUse(Collections.emptyList());
        service.setFiltersInUse(Arrays.asList(new BeforeCurrentTimeFilter()));
        Assert.assertEquals("no flights set up expected empty list",
                Collections.EMPTY_LIST, service.getFilteredFlights());
    }

    @Test
    public void runningWithOneFilter() {
        service.setFlightsInUse(testFlights);
        service.setFiltersInUse(Arrays.asList(new BeforeCurrentTimeFilter()));
        Assert.assertFalse("run with one filter expect not empty list",
                service.getFilteredFlights().isEmpty());
    }

    @Test
    public void runningWithTwoFilters() {
        service.setFlightsInUse(testFlights);
        service.setFiltersInUse(Arrays.asList(new BeforeCurrentTimeFilter(),
                new TimeBetweenSegmentsFilter()));
        Assert.assertFalse("run with two filters expect not empty list",
                service.getFilteredFlights().isEmpty());
    }

    @Test
    public void runningWithThreeFilters() {
        service.setFlightsInUse(testFlights);
        service.setFiltersInUse(Arrays.asList(new BeforeCurrentTimeFilter(),
                new TimeBetweenSegmentsFilter(),
                new SegmentStartsAfterFinishesFilter()));
        Assert.assertFalse("run with three filters expect not empty list",
                service.getFilteredFlights().isEmpty());
    }

    @Test
    public void runningWithNoFilters() {
        service.setFlightsInUse(testFlights);
        service.setFiltersInUse(Collections.emptyList());
        Assert.assertEquals("run with no filters expect source List",
                service.getFilteredFlights(), testFlights);
    }
    @Test
    public void runningWithNull(){
        service.setFlightsInUse(null);
        service.setFiltersInUse(null);
        Assert.assertTrue("running with null expect empty list",service.getFilteredFlights().isEmpty());
    }

}
