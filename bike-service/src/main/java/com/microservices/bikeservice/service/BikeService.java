package com.microservices.bikeservice.service;

import com.microservices.bikeservice.entity.Bike;
import com.microservices.bikeservice.repository.BikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BikeService {
    
    private final BikeRepository bikeRepository;

    public List<Bike> getAllBikes(){
        return this.bikeRepository.findAll();
    }

    public Optional<Bike> getBikeById(int bikeId){
        return this.bikeRepository.findById(bikeId);
    }

    public Bike save(Bike bike){
        return this.bikeRepository.save(bike);
    }
    
    public List<Bike> findBikeByUserId(int userId) { return this.bikeRepository.findBikeByUserId(userId); }


}
