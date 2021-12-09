package com.ates.flights.repositories;

import com.ates.flights.entities.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BaggageRepository extends JpaRepository<Baggage, Integer> {
    @Query(value = "SELECT coalesce(SUM(BAGGAGE.PIECES),0) FROM (FLIGHT INNER JOIN BAGGAGE ON FLIGHT.FLIGHT_ID  = BAGGAGE.FLIGHT_ID) WHERE FLIGHT.departure_airport_iata_code =:code AND flight.departure_date =:date", nativeQuery = true)
    Integer piecesOfBaggageDepartingFromThisAirport(String code, String date);

    @Query(value = "SELECT coalesce(SUM(BAGGAGE.PIECES),0) FROM (FLIGHT INNER JOIN BAGGAGE ON FLIGHT.FLIGHT_ID  = BAGGAGE.FLIGHT_ID) WHERE FLIGHT.arrival_airport_iata_code =:code AND flight.departure_date =:date", nativeQuery = true)
    Integer piecesOfBaggageArrivingToThisAirport(String code, String date);
}