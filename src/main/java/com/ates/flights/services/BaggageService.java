package com.ates.flights.services;


import com.ates.flights.entities.Baggage;
import com.ates.flights.repositories.BaggageRepository;
import org.springframework.stereotype.Service;

@Service
public class BaggageService {
    private final BaggageRepository baggageRepository;

    public BaggageService(BaggageRepository baggageRepository) {
        this.baggageRepository = baggageRepository;
    }

    public Iterable<Baggage> list() {
        return baggageRepository.findAll();
    }

}
