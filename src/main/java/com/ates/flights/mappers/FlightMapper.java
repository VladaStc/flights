package com.ates.flights.mappers;

import com.ates.flights.dto.FlightWeightDto;
import com.ates.flights.entities.Flight;


public class FlightMapper {
    public static FlightWeightDto mapToFlightWeightDto(Flight flight, double baggageWeight, double cargoWeight, double totalWeight){
        FlightWeightDto flightWeightDto = new FlightWeightDto();
        flightWeightDto.setFlightNumber(flight.getFlightNumber());
        flightWeightDto.setDepartureDate(flight.getDepartureDate());
        flightWeightDto.setCargoWeight((float) cargoWeight);
        flightWeightDto.setBaggageWeight((float) baggageWeight);
        flightWeightDto.setTotalWeight((float) totalWeight);
        return flightWeightDto;
    }
}
