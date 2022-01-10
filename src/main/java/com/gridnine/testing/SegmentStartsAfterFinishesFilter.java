package com.gridnine.testing;

public class SegmentStartsAfterFinishesFilter implements Filter {
    @Override
    public boolean match(Flight flight) {
        return false;
    }
}
