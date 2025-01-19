package com.example.Hari.NewSpringBatch.Repo;



import com.example.Hari.NewSpringBatch.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
