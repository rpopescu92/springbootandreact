package com.react.example.cardatabase.web;

import com.react.example.cardatabase.domain.entity.Car;
import com.react.example.cardatabase.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public Iterable<Car> getCars() {
        return carService.getAllCars();
    }
}
