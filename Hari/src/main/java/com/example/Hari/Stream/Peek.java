package com.example.Hari.Stream;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
// printing the list
public class Peek {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2,4,5);

        List<Integer> squareValue = numbers.stream().
                peek(System.out::println).
                map(number->number*number).
                peek(System.out::println).toList();
                }
}
