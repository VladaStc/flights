package com.ates.flights.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightWeightDto implements Serializable {
    private  Integer flightNumber;
    private  String departureDate;
    private  Float cargoWeight;
    private  Float baggageWeight;
    private  Float totalWeight;
}
