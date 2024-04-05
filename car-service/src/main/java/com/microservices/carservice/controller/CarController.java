package com.microservices.carservice.controller;

import com.microservices.carservice.entity.Car;
import com.microservices.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(){
        List<Car> cars = this.carService.getAllCars();
        if (cars.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/{car_id}")
    public ResponseEntity<Car> getCarById(@PathVariable("car_id") int carId){
        Optional<Car> Car = this.carService.getCarById(carId);
        return Car.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }

    @PostMapping()
    public ResponseEntity<Car> getCarById(@RequestBody Car car){
        Car carSaved = this.carService.save(car);

       return new ResponseEntity<>(carSaved, HttpStatus.CREATED);
    }

    @GetMapping("/find-by-user/{user_id}")
    public ResponseEntity<List<Car>> findByCarId(@PathVariable("user_id") int userId){
        List<Car> cars = this.carService.findCarByUserId(userId);
        if (cars.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

}
