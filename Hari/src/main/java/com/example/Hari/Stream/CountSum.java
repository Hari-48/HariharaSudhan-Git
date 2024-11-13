package com.example.Hari.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CountSum {
    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        long count = values.stream().mapToInt(Integer::intValue).count();
        System.out.println("Count ->" + count);
        int sum = values.stream().mapToInt(Integer::intValue).sum();
        System.out.println("sum ->" + sum);
    }
}
