package com.example.employee_service.Repository;

import com.example.employee_service.Model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepo {
    private List<Employee> employees = new ArrayList<>();
    public Employee addEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }
    public Employee findById(Long id) {
        return employees
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }
    public List<Employee> findAll() {
        return employees;
    }

    public List<Employee> findByDepId(Long depId) {
        return employees
                .stream()
                .filter(a -> a.getDepartmentId().equals(depId)).toList();
    }
}
