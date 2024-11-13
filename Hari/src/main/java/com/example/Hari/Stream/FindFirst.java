package com.example.Hari.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindFirst {
    public static void main(String[] args) {

        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Optional<Integer> findFirst = values.stream().filter(n -> n % 2 == 0).findFirst();

        Boolean anyMatch = values.stream().anyMatch(n -> n % 2 == 0);


        System.out.println(findFirst.get());

        System.out.println(anyMatch);
    }
}
