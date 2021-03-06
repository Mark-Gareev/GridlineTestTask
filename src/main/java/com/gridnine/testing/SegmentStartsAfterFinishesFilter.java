package com.gridnine.testing;

import java.util.List;

/**
 * class describing filter witch winnow flight with segments witch have departure date before arrival date.
 */
public class SegmentStartsAfterFinishesFilter implements FlightFilter {
    @Override
    public boolean match(Flight flight) {
        if(flight == null){
            return false;
        }
        List<Segment> segments = flight.getSegments();
        boolean result = true;
        for (Segment segment:
                segments) {
            if(segment.getDepartureDate().isAfter(segment.getArrivalDate())){
                result = false;
                break;
            }
        }
        return result;
    }
}
