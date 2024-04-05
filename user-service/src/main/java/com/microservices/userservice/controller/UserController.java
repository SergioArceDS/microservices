package com.microservices.userservice.controller;

import com.microservices.userservice.entity.User;
import com.microservices.userservice.model.Car;
import com.microservices.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = this.userService.getAllUsers();
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable("user_id") int userId){
        Optional<User> user = this.userService.getUserById(userId);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }

    @PostMapping()
    public ResponseEntity<User> getUserById(@RequestBody User user){
       User userSaved = this.userService.save(user);

       return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @GetMapping("/cars/{user_id}")
    public ResponseEntity<List<Car>> getCarByUserId(@PathVariable("user_id") int userId){
        List<Car> cars = this.userService.getCars(userId);
        if(cars.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping("/savecar/{user_id}")
    public ResponseEntity<Car> saveCar(@PathVariable int userId, @RequestBody Car car){
        Car newCar = userService.saveCar(userId, car);

        return new ResponseEntity<>(car, HttpStatus.OK);
    }

}
