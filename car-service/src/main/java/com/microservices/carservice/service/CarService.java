package com.microservices.carservice.service;

import com.microservices.carservice.entity.Car;
import com.microservices.carservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarService {
    
    private final CarRepository carRepository;

    public List<Car> getAllCars(){
        return this.carRepository.findAll();
    }

    public Optional<Car> getCarById(int carId){
        return this.carRepository.findById(carId);
    }

    public Car save(Car car){
        return this.carRepository.save(car);
    }
    
    public List<Car> findCarByUserId(int userId) { return this.carRepository.findCarByUserId(userId); }


}
