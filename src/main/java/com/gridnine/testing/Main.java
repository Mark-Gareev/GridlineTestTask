package com.gridnine.testing;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String...args){
        System.out.println("To start!");
        FlightFilterService flightFilterService = new FlightFilterService();
        flightFilterService.setFlightsInUse(FlightBuilder.createFlights());
        List<Filter> filters = new LinkedList<>();
        flightFilterService.setFiltersInUse(filters);
        List<Flight>flights = flightFilterService.getFilteredFlights();
        for (Flight flight:
             flights) {
            System.out.println(flight.toString());
        }
    }
}
