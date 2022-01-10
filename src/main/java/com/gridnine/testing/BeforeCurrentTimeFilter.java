package com.gridnine.testing;

public class BeforeCurrentTimeFilter implements Filter {
    @Override
    public boolean match(Flight flight) {
        return false;
    }
}
