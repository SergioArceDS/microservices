package com.microservices.userservice.service;

import com.microservices.userservice.entity.User;
import com.microservices.userservice.feignclients.CarFeignClient;
import com.microservices.userservice.model.Bike;
import com.microservices.userservice.model.Car;
import com.microservices.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final CarFeignClient carFeignClient;

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public Optional<User> getUserById(int userId){
        return this.userRepository.findById(userId);
    }

    public User save(User user){
        return this.userRepository.save(user);
    }

    public List<Car> getCars(int id){
        List cars = restTemplate.getForObject("http://localhost:8002/car/find-by-user/" + id, List.class);

        return cars;
    }

    public List<Bike> getBikes(int id){
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/find-by-user/" + id, List.class);

        return bikes;
    }

    public Car saveCar(int id, Car car){

        car.setUserId(id);
        return this.carFeignClient.save(car);
    }

}
