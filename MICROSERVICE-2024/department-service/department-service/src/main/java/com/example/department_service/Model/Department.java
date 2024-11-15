package com.example.department_service.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Data
@Getter
@Setter
public class Department {

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    private Long id;
    private String name ;
    private List<Employee> employees = new ArrayList<>();

}
