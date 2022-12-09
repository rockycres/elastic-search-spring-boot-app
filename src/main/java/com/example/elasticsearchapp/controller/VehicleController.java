package com.example.elasticsearchapp.controller;

import com.example.elasticsearchapp.document.Person;
import com.example.elasticsearchapp.document.Vehicle;
import com.example.elasticsearchapp.service.PersonService;
import com.example.elasticsearchapp.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService service;

    @PostMapping
    public void index(@RequestBody final Vehicle vehicle){
        service.index(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle findById(@PathVariable  final String id){
        return service.getById(id);
    }
}
