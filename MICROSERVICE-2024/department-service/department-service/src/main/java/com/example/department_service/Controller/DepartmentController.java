package com.example.department_service.Controller;

import com.example.department_service.Client.EmployeeClient;
import com.example.department_service.Model.Department;
import com.example.department_service.Repository.DepartmentRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.EmptyReaderEventListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/department")
public class DepartmentController {

    // private static final Logger log = LoggerFactory.getLogger(Department.class);

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping()
    public Department addDepartment(@RequestBody Department department) {
        return departmentRepo.addDepartment(department);
    }

    @GetMapping()
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        return departmentRepo.findById(id);
    }

    @GetMapping("/with-employee")
    public List<Department> findAllWithEmployee() {
        List<Department> departments= departmentRepo.findAll();

//        return departments.stream()
//                .peek(department -> department.setEmployees(employeeClient.findByDepId(department.getId())))
//                .collect(Collectors.toList());

        departments.forEach(department->department.setEmployees(employeeClient.findByDepId(department.getId())));
        return departments;
    }




}
