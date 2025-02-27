package com.miraeclimate.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.miraeclimate.demo.entity.Person;
import com.miraeclimate.demo.repository.PersonRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonService {
  private final PersonRepository personRepository;

  public Person createPerson(Person person) {
    return personRepository.save(person);
  }

  public List<Person> getPersons() {
    return personRepository.findAll();
  }

  public Person getPerson(Long id) {
    Optional<Person> person = personRepository.findById(id);

    if (person.isPresent())
      return person.get();

    return null;
  }

  public void deletePerson(Long id) {
    personRepository.deleteById(id);
  }
}
