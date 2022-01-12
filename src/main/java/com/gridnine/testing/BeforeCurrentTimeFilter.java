package com.gridnine.testing;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * class describing filter, witch winnow flights that starts before current time
 */
public class BeforeCurrentTimeFilter implements Filter {
    @Override
    public boolean match(Flight flight) {
        List<Segment> segments = flight.getSegments();
        boolean result = true;
        for (Segment segment:
             segments) {
            if(segment.getDepartureDate().isBefore(LocalDateTime.now())){
                result = false;
                break;
            }
        }
        return result;
    }
}
