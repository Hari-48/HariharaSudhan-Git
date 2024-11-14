package com.example.employee_service.Controller;

import com.example.employee_service.Model.Employee;
import com.example.employee_service.Repository.EmployeeRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepo employeeRepo;

    @PostMapping()
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeRepo.addEmployee(employee);
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @GetMapping("/{empId}")
    public Employee findByEmployeeId(@PathVariable Long empId) {
        return employeeRepo.findById(empId);
    }

    @GetMapping("department/{depId}")
    public List<Employee> findByDepId(@PathVariable Long depId) {
        return employeeRepo.findByDepId(depId);
    }


}
