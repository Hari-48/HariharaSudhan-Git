package com.example.Hari.Stream;

import java.util.Arrays;
import java.util.List;

public class Filter {
    public static void main(String[] args) {

        // List<?> values = Arrays.asList(1,2,"hari","sudhan");
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        values.stream().
                filter(n -> n % 2 == 0).
                forEach(System.out::println);

    }
}
