package com.example.Hari.Batchprocessing.Repository;

import com.example.Hari.Batchprocessing.Entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
