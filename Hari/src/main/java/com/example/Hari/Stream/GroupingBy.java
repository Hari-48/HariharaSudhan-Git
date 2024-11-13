package com.example.Hari.Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingBy {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("HARI","IT",4000L),
                new Employee("ABI","IT",5000L),
                new Employee("ANU","MECH",4000L),
                new Employee("ANANTH","CSE",5000L),
                new Employee("RAJA","LAW",8000L),
                new Employee("GANESH","IT",5000L));

//        employees.stream().filter(name->name.getName().equals("HARI"))
//                .map(name->name.getDepartment()).forEach(System.out::println);


        Map<String,List<Employee>> collect =employees.stream().collect(Collectors.groupingBy(name->name.getDepartment()));
        System.out.println(collect);
        collect.forEach((department,employeList)->{
            System.out.println("department:"+department);
            employeList.forEach(System.out::println);
        });



        Map<Boolean , List<Employee>> partitioningBySalary = employees.stream().collect(Collectors.partitioningBy(employee->employee.getSalary()>4100L));

        System.out.println("Employee with salary greater than 4100L");
        partitioningBySalary.get(true).forEach(System.out::println);

        System.out.println("Employee with salary Less than 4100L");
        partitioningBySalary.get(false).forEach(System.out::println);


    }
}
