package com.example.Hari.Stream;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Employee {
    private String name;

    public Employee(String name, String department, Long salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    private String department;
    private Long salary;
}
