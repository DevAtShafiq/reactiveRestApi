package com.example.person.controller;

import com.example.person.domain.Person;
import com.example.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public void savePeople(@RequestBody Person person)
    {
        personService.savePerson(person);
    }
}
