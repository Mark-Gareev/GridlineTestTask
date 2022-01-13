package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

/**
 * class describing filter, witch winnow flights that starts before current time
 */
public class BeforeCurrentTimeFilter implements FlightFilter {
    @Override
    public boolean match(Flight flight) {
        if(flight == null){
            return false;
        }
        List<Segment> segments = flight.getSegments();
        boolean result = true;
        LocalDateTime now = LocalDateTime.now();
        for (Segment segment:
             segments) {
            if(segment.getDepartureDate().isBefore(now)){
                result = false;
                break;
            }
        }
        return result;
    }
}
