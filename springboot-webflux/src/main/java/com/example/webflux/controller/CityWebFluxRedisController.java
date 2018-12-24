package com.example.webflux.controller;

import com.example.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("/city2")
@RestController
public class CityWebFluxRedisController {

    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    @RequestMapping("/{id}")
    public Mono<City> findCityById(@PathVariable("id") Long id) {

        String key = "city_" + id;
        ReactiveValueOperations reactiveValueOperations = reactiveRedisTemplate.opsForValue();
        Mono<City> mono = reactiveValueOperations.get(key);
        return mono;
    }

    @PostMapping
    public Mono<City> saveCity(@RequestBody City city) {
        String key = "city_" + city.getId();
        ReactiveValueOperations<String, City> operations = reactiveRedisTemplate.opsForValue();
        return operations.getAndSet(key, city);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        String key = "city_" + id;
        return reactiveRedisTemplate.delete(key);
    }
}
