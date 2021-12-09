package com.ates.flights.services;

import com.ates.flights.dto.FlightWeightDto;
import com.ates.flights.dto.FlightsAndBaggageDto;
import com.ates.flights.entities.Baggage;
import com.ates.flights.entities.Cargo;
import com.ates.flights.entities.Flight;
import com.ates.flights.repositories.BaggageRepository;
import com.ates.flights.repositories.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private FlightRepository mockFlightRepository;
    @Mock
    private BaggageRepository mockBaggageRepository;

    private FlightService flightServiceUnderTest;

    @BeforeEach
    void setUp() {
        flightServiceUnderTest = new FlightService(mockFlightRepository, mockBaggageRepository);
    }

    @Test
    void testList() {
        // Setup
        // Configure FlightRepository.findAll(...).
        final Flight flight = new Flight();
        flight.setId(0);
        flight.setFlightNumber(3704);
        flight.setDepartureAirportIataCode("SEA");
        flight.setArrivalAirportIataCode("KRK");
        flight.setDepartureDate("2015-02-13T06:43:36 -01:00");
        final Baggage baggage = new Baggage();
        baggage.setId(0);
        baggage.setWeight(781);
        baggage.setWeightUnit("lb");
        baggage.setPieces(565);
        baggage.setFlight(new Flight());
        flight.setBaggage(List.of(baggage));
        final Cargo cargo = new Cargo();
        cargo.setId(0);
        cargo.setWeight(673);
        cargo.setWeightUnit("lb");
        cargo.setPieces(989);
        cargo.setFlight(new Flight());
        flight.setCargo(List.of(cargo));
        final List<Flight> flights = List.of(flight);
        when(mockFlightRepository.findAll()).thenReturn(flights);

        // Run the test
        final Iterable<Flight> result = flightServiceUnderTest.list();

        // Verify the results
    }

    @Test
    void testList_FlightRepositoryReturnsNoItems() {
        // Setup
        when(mockFlightRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final Iterable<Flight> result = flightServiceUnderTest.list();

        // Verify the results
    }

    @Test
    void testWeightForRequestedFlight() {
        // Setup
        final FlightWeightDto expectedResult = new FlightWeightDto(3704, "2015-02-13T06:43:36 -01:00", 1495.5555f, 1735.5555f, 3231.111f);

        // Configure FlightRepository.findByFlightNumberAndDepartureDate(...).
        final Flight flight = new Flight();
        flight.setId(0);
        flight.setFlightNumber(3704);
        flight.setDepartureAirportIataCode("2015-02-13T06:43:36 -01:00");
        flight.setArrivalAirportIataCode("KRK");
        flight.setDepartureDate("2015-02-13T06:43:36 -01:00");
        final Baggage baggage = new Baggage();
        baggage.setId(0);
        baggage.setWeight(781);
        baggage.setWeightUnit("lb");
        baggage.setPieces(371);
        baggage.setFlight(new Flight());
        flight.setBaggage(List.of(baggage));
        final Cargo cargo = new Cargo();
        cargo.setId(0);
        cargo.setWeight(673);
        cargo.setWeightUnit("lb");
        cargo.setPieces(989);
        cargo.setFlight(new Flight());
        flight.setCargo(List.of(cargo));
        when(mockFlightRepository.findByFlightNumberAndDepartureDate(3704, "2015-02-13T06:43:36 -01:00")).thenReturn(flight);

        // Run the test
        final FlightWeightDto result = flightServiceUnderTest.weightForRequestedFlight(3704, "2015-02-13T06:43:36 -01:00", true);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFlightsAndBaggageInfo() {
        // Setup
        final FlightsAndBaggageDto expectedResult = new FlightsAndBaggageDto();
        expectedResult.setDepartureDate("2015-02-13T06:43:36 -01:00");
        expectedResult.setIATACode("SEA");
        expectedResult.setNumberOfDepartures(1);
        expectedResult.setNumberOfArrivals(0);
        expectedResult.setNumberOfBaggageArriving(0);
        expectedResult.setNumberOfBaggageDeparting(2132);

        when(mockFlightRepository.countByArrivalAirportIataCodeAndDepartureDate("SEA", "2015-02-13T06:43:36 -01:00")).thenReturn(0);
        when(mockFlightRepository.countByDepartureAirportIataCodeAndDepartureDate("SEA", "2015-02-13T06:43:36 -01:00")).thenReturn(1);
        when(mockBaggageRepository.piecesOfBaggageDepartingFromThisAirport("SEA", "2015-02-13T06:43:36 -01:00")).thenReturn(2132);
        when(mockBaggageRepository.piecesOfBaggageArrivingToThisAirport("SEA", "2015-02-13T06:43:36 -01:00")).thenReturn(0);

        // Run the test
        final FlightsAndBaggageDto result = flightServiceUnderTest.flightsAndBaggageInfo("SEA", "2015-02-13T06:43:36 -01:00");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
