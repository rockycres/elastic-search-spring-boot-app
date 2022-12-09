package com.example.elasticsearchapp.controller;

import com.example.elasticsearchapp.document.Person;
import com.example.elasticsearchapp.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @PostMapping
    public void save(@RequestBody final Person person){
        service.save(person);
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable  final String id){
        return service.findById(id);
    }
}
