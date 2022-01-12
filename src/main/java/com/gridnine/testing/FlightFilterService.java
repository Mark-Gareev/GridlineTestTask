package com.gridnine.testing;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
Вам нужно написать небольшой модуль, который будет заниматься фильтрацией набора перелётов
согласно различным правилам. Правил фильтрации может быть очень много. Также наборы
перелётов могут быть очень большими. Правила могут выбираться и задаваться динамически в
зависимости от контекста выполнения операции фильтрации.
 */
public class FlightFilterService {
    private List<Flight> flightsInUse = Collections.emptyList();
    private List<Filter> filtersInUse = Collections.emptyList();


    public List<Flight> getFilteredFlights() {
        LinkedList<Flight> result = new LinkedList<>();

        for (Flight targetFlight :
                flightsInUse) {
            boolean flightMatchFlag = true;
            for (Filter filter :
                    filtersInUse) {
                if (!filter.match(targetFlight)) {
                    flightMatchFlag = false;
                    break;
                }
            }
            if (flightMatchFlag) {
                result.add(targetFlight);
            }
        }

        return result;
    }

    public void setFiltersInUse(List<Filter> filtersInUse) {
        if (filtersInUse != null) {
            this.filtersInUse = filtersInUse;
        }else{
            this.filtersInUse = Collections.emptyList();
        }
    }

    public void setFlightsInUse(List<Flight> flightsInUse) {
        if (flightsInUse != null) {
            this.flightsInUse = flightsInUse;
        }else{
            this.flightsInUse = Collections.emptyList();
        }
    }
}
