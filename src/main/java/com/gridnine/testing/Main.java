package com.gridnine.testing;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String...args){
        System.out.println("Demo");
        FlightFilterService flightFilterService = new FlightFilterService();
        flightFilterService.setFlightsInUse(FlightBuilder.createFlights());

        List<FlightFilter> flightFilters = Collections.singletonList(new BeforeCurrentTimeFilter());
        System.out.println("Sort flights witch depart in the past");
        flightFilterService.setFiltersInUse(flightFilters);
        List<Flight>filteredFlights = flightFilterService.getFilteredFlights();
        filteredFlights.forEach(System.out::println);

        flightFilters = Collections.singletonList(new SegmentStartsAfterFinishesFilter());
        System.out.println("Sort flights witch departs before it arrives");
        flightFilterService.setFiltersInUse(flightFilters);
        filteredFlights = flightFilterService.getFilteredFlights();
        for (Flight filteredFlight : filteredFlights) {
            System.out.println(filteredFlight);
        }

        flightFilters = Collections.singletonList(new TimeBetweenSegmentsFilter());
        flightFilterService.setFiltersInUse(flightFilters);
        System.out.println("sort flights with more than two hours between segments");
        filteredFlights = flightFilterService.getFilteredFlights();
        filteredFlights.forEach(System.out::println);
    }
}
