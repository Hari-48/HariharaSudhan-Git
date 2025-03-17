package com.example.Hari.Annotation;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Student {

    //final fields
    private final String studentName;

    //non -final fields
    private String role;

    //non - null fields
    @NonNull
    private String age ;



}

