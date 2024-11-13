package com.example.Hari.Stream;

import java.util.ArrayList;
import java.util.List;

public class Limit {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0 ; i<100;i++){
            numbers.add(i);
        }
        System.out.print(numbers);
        numbers.stream().limit(5).forEach(System.out::println);
    }
}
