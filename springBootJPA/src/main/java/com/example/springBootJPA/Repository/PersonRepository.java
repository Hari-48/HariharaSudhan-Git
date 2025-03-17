package com.example.springBootJPA.Repository;


import com.example.springBootJPA.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
