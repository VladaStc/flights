package com.ates.flights.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "baggage")
public class Baggage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "weight_unit", nullable = false)
    private String weightUnit;

    @Column(name = "pieces", nullable = false)
    private Integer pieces;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, targetEntity = Flight.class)
    @JoinColumn(name = "flight_id")
    @JsonBackReference
    private Flight flight;
}