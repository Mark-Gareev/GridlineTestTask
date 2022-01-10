package com.gridnine.testing;

/**
 * Interface presents a filter of flights.
 */
public interface Filter {
    /**
     * method to match flight with filter
     * @param flight - flight to analyse
     * @return true, if flight matches this filter, false if it does not.
     */
    boolean match(Flight flight);
}
