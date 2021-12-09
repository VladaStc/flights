package com.ates.flights.controllers;

import com.ates.flights.dto.FlightWeightDto;
import com.ates.flights.dto.FlightsAndBaggageDto;
import com.ates.flights.entities.Flight;
import com.ates.flights.services.FlightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/list")
    public Iterable<Flight> list() {
        return flightService.list();
    }

    @GetMapping("/weightForRequestedFlight")
    public FlightWeightDto weightForRequestedFlight(@RequestParam Integer flightNumber, @RequestParam String departureDate, @RequestParam Boolean isKilograms) {
        return flightService.weightForRequestedFlight(flightNumber, departureDate, isKilograms);
    }

    @GetMapping("/flightsAndBaggageInfo")
    public FlightsAndBaggageDto flightsAndBaggageInfo(@RequestParam String code, @RequestParam String date){
        return flightService.flightsAndBaggageInfo(code, date);
    }
}
