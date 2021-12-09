package com.ates.flights.controllers;

import com.ates.flights.entities.Baggage;
import com.ates.flights.services.BaggageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/baggage")
public class BaggageController {

    private final BaggageService baggageService;

    public BaggageController(BaggageService baggageService) {
        this.baggageService = baggageService;
    }
    @GetMapping("/list")
    public Iterable<Baggage> list() {
        return baggageService.list();
    }
}
