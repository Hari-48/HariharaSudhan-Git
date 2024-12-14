package com.example.department_service.Model;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class Employee {

    private Long id ;
    private Long departmentId;
    private String name;
    private Long age;
    private String position;
}
