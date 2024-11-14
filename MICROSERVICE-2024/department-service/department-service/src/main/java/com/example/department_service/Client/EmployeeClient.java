package com.example.department_service.Client;

import com.example.department_service.Model.Employee;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@FeignClient(name = "employee-service",url = "http://localhost:9002/employee")
public interface  EmployeeClient {
    @GetMapping("department/{depId}")
    public List<Employee> findByDepId(@PathVariable Long depId) ;
}
