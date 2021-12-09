package com.ates.flights.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FlightDto implements Serializable {
    private final Integer id;
    private final Integer flightNumber;
    private final String departureAirportIataCode;
    private final String arrivalAirportIataCode;
    private final String departureDate;
    private final List<BaggageDto> baggage;
    private final List<CargoDto> cargo;
}
