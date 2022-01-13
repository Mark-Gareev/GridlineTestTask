package com.gridnine.testing;

import java.time.Duration;
import java.util.List;

public class TimeBetweenSegmentsFilter implements FlightFilter {
    @Override
    public boolean match(Flight flight) {
        if(flight == null){
            return false;
        }
        List<Segment> segments = flight.getSegments();
        long timeCounter = 0;
        int segmentsSize = segments.size();
        boolean result = true;
        Segment first;
        Segment second;


        for (int i = 0; i < segmentsSize - 1; i++) {
            first = segments.get(i);
            second = segments.get(i + 1);
            timeCounter += Duration.between(first.getArrivalDate(), second.getDepartureDate()).toMinutes();
            if (timeCounter > 120) {
                result = false;
                break;
            }
        }

        return result;
    }
}
