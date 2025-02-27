package com.miraeclimate.demo.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.miraeclimate.demo.entity.Person;
import com.miraeclimate.demo.service.PersonService;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/v1/persons")
@AllArgsConstructor
public class PersonController {
  private final PersonService personService;

  @GetMapping()
  public ResponseEntity<List<Person>> getPersons() {
    return ResponseEntity.ok(personService.getPersons());
  }

}
