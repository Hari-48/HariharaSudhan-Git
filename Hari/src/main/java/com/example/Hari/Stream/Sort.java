package com.example.Hari.Stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sort {
    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

//        values.stream().sorted().forEach(System.out::println);
//        values.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
//
        List<String> words = Arrays.asList("Hari","Sudhan","Raja","Abi","Aravindh");

        //words.stream().sorted().forEach(System.out::println);

       // words.stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);
        words.stream().sorted(Comparator.comparingInt(String::length).reversed()).forEach(System.out::println);



    }
}
