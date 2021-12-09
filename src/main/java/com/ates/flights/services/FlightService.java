package com.ates.flights.services;

import com.ates.flights.dto.FlightWeightDto;
import com.ates.flights.dto.FlightsAndBaggageDto;
import com.ates.flights.entities.Baggage;
import com.ates.flights.entities.Cargo;
import com.ates.flights.entities.Flight;
import com.ates.flights.mappers.FlightMapper;
import com.ates.flights.repositories.BaggageRepository;
import com.ates.flights.repositories.FlightRepository;
import com.ates.flights.utils.WeightConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final BaggageRepository baggageRepository;

    public FlightService(FlightRepository flightRepository, BaggageRepository baggageRepository) {
        this.flightRepository = flightRepository;
        this.baggageRepository = baggageRepository;
    }

    public Iterable<Flight> list() {
        return flightRepository.findAll();
    }

    public FlightWeightDto weightForRequestedFlight(Integer flightNumber, String departureDate, Boolean isKilograms) {
        Flight flight = flightRepository.findByFlightNumberAndDepartureDate(flightNumber, departureDate);

        double baggageWeight = calculateBaggageWeight(flight.getBaggage(), isKilograms);
        double cargoWeight = calculateCargoWeight(flight.getCargo(), isKilograms);
        double totalWeight = baggageWeight + cargoWeight;

        return FlightMapper.mapToFlightWeightDto(flight, baggageWeight, cargoWeight, totalWeight);
    }

    private double calculateBaggageWeight(List<Baggage> baggageList, Boolean isKilograms) {
        return baggageList.stream().mapToDouble(baggage -> {
            if (isKilograms) {
                if (baggage.getWeightUnit() == "kg") {
                    return baggage.getWeight();
                } else {
                    return WeightConverter.convertLbToKg(baggage.getWeight().floatValue());
                }
            } else {
                if (baggage.getWeightUnit() == "lb") {
                    return baggage.getWeight();
                } else {
                    return WeightConverter.convertKgToLb(baggage.getWeight().floatValue());
                }
            }
        }).sum();
    }

    private double calculateCargoWeight(List<Cargo> cargoList, Boolean isKilograms) {
        return cargoList.stream().mapToDouble(cargo -> {
            if (isKilograms) {
                if (cargo.getWeightUnit() == "kg") {
                    return cargo.getWeight();
                } else {
                    return WeightConverter.convertLbToKg(cargo.getWeight().floatValue());
                }
            } else {
                if (cargo.getWeightUnit() == "lb") {
                    return cargo.getWeight();
                } else {
                    return WeightConverter.convertKgToLb(cargo.getWeight().floatValue());
                }
            }
        }).sum();
    }

    public FlightsAndBaggageDto flightsAndBaggageInfo(String code, String date) {
        int numberArrival = flightRepository.countByArrivalAirportIataCodeAndDepartureDate(code, date);
        int numberDeparture = flightRepository.countByDepartureAirportIataCodeAndDepartureDate(code, date);
        int piecesDeparting = baggageRepository.piecesOfBaggageDepartingFromThisAirport(code, date);
        int piecesArrival = baggageRepository.piecesOfBaggageArrivingToThisAirport(code, date);
        return create(code, date, numberArrival, numberDeparture, piecesDeparting, piecesArrival);
    }

    private FlightsAndBaggageDto create(String code, String date, int numberArrival, int numberDeparture, int piecesDeparting, int piecesArrival) {
        FlightsAndBaggageDto flightsAndBaggageDto = new FlightsAndBaggageDto();
        flightsAndBaggageDto.setDepartureDate(date);
        flightsAndBaggageDto.setIATACode(code);
        flightsAndBaggageDto.setNumberOfBaggageArriving(piecesArrival);
        flightsAndBaggageDto.setNumberOfBaggageDeparting(piecesDeparting);
        flightsAndBaggageDto.setNumberOfArrivals(numberArrival);
        flightsAndBaggageDto.setNumberOfDepartures(numberDeparture);
        return flightsAndBaggageDto;
    }
}
