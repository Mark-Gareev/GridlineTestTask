package com.gridnine.testing;

/**
 * Interface marking a filter of flights.
 */
public interface FlightFilter {
    /**
     * method to match flight with filter
     * @param flight - flight to analyse
     * @return true, if flight matches this filter, false if it does not.
     */
    boolean match(Flight flight);
}
