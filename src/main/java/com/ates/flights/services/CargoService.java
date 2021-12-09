package com.ates.flights.services;

import com.ates.flights.entities.Cargo;
import com.ates.flights.repositories.CargoRepository;
import org.springframework.stereotype.Service;

@Service
public class CargoService {
    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Iterable<Cargo> list() {
        return cargoRepository.findAll();
    }

}
