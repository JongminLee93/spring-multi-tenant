package com.miraeclimate.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "person")
public class Person {
  @Id
  private Long id;
  private String name;
  private Integer age;
  private String email;
}
