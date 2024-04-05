package com.microservices.userservice.feignclients;

import com.microservices.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "car-service", url = "localhost:8002")
@RequestMapping("/car")
public interface CarFeignClient {

    @PostMapping
    public Car save(@RequestBody Car car);
}
