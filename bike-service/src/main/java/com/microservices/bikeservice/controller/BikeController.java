package com.microservices.bikeservice.controller;

import com.microservices.bikeservice.entity.Bike;
import com.microservices.bikeservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAllBikes(){
        List<Bike> bikes = this.bikeService.getAllBikes();
        if (bikes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(bikes, HttpStatus.OK);
    }

    @GetMapping("/{bike_id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable("bike_id") int bikeId){
        Optional<Bike> Bike = this.bikeService.getBikeById(bikeId);
        return Bike.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }

    @PostMapping()
    public ResponseEntity<Bike> getBikeById(@RequestBody Bike bike){
        Bike bikeSaved = this.bikeService.save(bike);

       return new ResponseEntity<>(bikeSaved, HttpStatus.CREATED);
    }

    @GetMapping("/find-by-user/{user_id}")
    public ResponseEntity<List<Bike>> findByUserId(@PathVariable("user_id") int userId){
        List<Bike> bikes = this.bikeService.findBikeByUserId(userId);
        if (bikes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(bikes, HttpStatus.OK);
    }

}
