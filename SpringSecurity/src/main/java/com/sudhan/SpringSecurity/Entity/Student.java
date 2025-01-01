package com.sudhan.SpringSecurity.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {


    @Override
    public String toString() {
        return "StudentController{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public int id;
    public String name;
    public String grade;

    public Student(int id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
}







