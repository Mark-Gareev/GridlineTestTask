package com.gridnine.testing;

import java.util.LinkedList;
import java.util.List;

/*
Вам нужно написать небольшой модуль, который будет заниматься фильтрацией набора перелётов
согласно различным правилам. Правил фильтрации может быть очень много. Также наборы
перелётов могут быть очень большими. Правила могут выбираться и задаваться динамически в
зависимости от контекста выполнения операции фильтрации.
 */
public class FlightFilterService {
    private List<Flight> flightsInUse;
    private List<Filter> filtersInUse;


    public List<Flight> getFilteredFlights() {
        List<Flight> result = new LinkedList<>();
        try {
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
        }catch (NullPointerException e){
            e.getMessage();
            e.printStackTrace();
        }
        return result;
    }

    public void setFiltersInUse(List<Filter> filtersInUse) {
        this.filtersInUse = filtersInUse;
    }

    public void setFlightsInUse(List<Flight> flightsInUse) {
        this.flightsInUse = flightsInUse;
    }
}
