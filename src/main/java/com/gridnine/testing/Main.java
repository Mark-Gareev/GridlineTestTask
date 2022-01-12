package com.gridnine.testing;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String...args){
        System.out.println("Demo");
        FlightFilterService flightFilterService = new FlightFilterService();
        flightFilterService.setFlightsInUse(FlightBuilder.createFlights());

        List<Filter>filters = Collections.singletonList(new BeforeCurrentTimeFilter());
        System.out.println("Sort flights witch depart in the past");
        flightFilterService.setFiltersInUse(filters);
        List<Flight>filteredFlights = flightFilterService.getFilteredFlights();
        filteredFlights.stream().forEach(System.out::println);

        filters = Collections.singletonList(new SegmentStartsAfterFinishesFilter());
        System.out.println("Sort flights witch departs before it arrives");
        flightFilterService.setFiltersInUse(filters);
        filteredFlights = flightFilterService.getFilteredFlights();
        filteredFlights.stream().forEach(System.out::println);

        filters = Collections.singletonList(new TimeBetweenSegmentsFilter());
        flightFilterService.setFiltersInUse(filters);
        System.out.println("sort flights with more than two hours between segments");
        filteredFlights = flightFilterService.getFilteredFlights();
        filteredFlights.stream().forEach(System.out::println);
    }
}
