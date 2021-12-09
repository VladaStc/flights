package com.ates.flights.controllers;

import com.ates.flights.entities.Cargo;
import com.ates.flights.services.CargoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cargo")
public class CargoController {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("/list")
    public Iterable<Cargo> list() {
        return cargoService.list();
    }
}
