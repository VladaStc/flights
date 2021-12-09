package com.ates.flights.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FlightsAndBaggageDto implements Serializable {
    private String departureDate;
    private String iATACode;
    private int numberOfDepartures;
    private int numberOfArrivals;
    private int numberOfBaggageArriving;
    private int numberOfBaggageDeparting;
}
