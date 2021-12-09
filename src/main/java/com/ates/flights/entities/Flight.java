package com.ates.flights.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @Column(name = "flight_id", nullable = false)
    private Integer id;

    @Column(name = "flight_number", nullable = false)
    private Integer flightNumber;

    @Column(name = "departure_airport_iata_code", nullable = false)
    private String departureAirportIataCode;

    @Column(name = "arrival_airport_iata_code", nullable = false)
    private String arrivalAirportIataCode;

    @Column(name = "departure_date", nullable = false)
    private String departureDate;

    @OneToMany(mappedBy = "flight", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    private List<Baggage> baggage = new ArrayList<>();

    @OneToMany(mappedBy = "flight", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    private List<Cargo> cargo = new ArrayList<>();

}