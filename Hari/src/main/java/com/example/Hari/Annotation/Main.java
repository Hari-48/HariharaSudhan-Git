package com.example.Hari.Annotation;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("John Doe","56");
        System.out.println("Student Name: " + student.getStudentName());
        System.out.println("Student age: " + student.getAge());

       // System.out.println("Student Age: " + student.getAge());
    }
}
