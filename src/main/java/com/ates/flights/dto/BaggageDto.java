package com.ates.flights.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaggageDto implements Serializable {
    private final Integer id;
    private final Integer weight;
    private final String weightUnit;
    private final Integer pieces;
}
