package com.miraeclimate.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.miraeclimate.demo.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
