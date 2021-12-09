package com.ates.flights.repositories;

import com.ates.flights.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    Flight findByFlightNumberAndDepartureDate(Integer flightNumber, String departureDate);

    Flight findByArrivalAirportIataCodeAndDepartureDate(String arrivalAirportIataCode, String departureDate);

    Flight findByDepartureAirportIataCodeAndDepartureDate(String departureAirportIataCode, String departureDate);

    int countByArrivalAirportIataCodeAndDepartureDate(String arrivalAirportIataCode, String departureDate);

    int countByDepartureAirportIataCodeAndDepartureDate(String departureAirportIataCode, String departureDate);

}