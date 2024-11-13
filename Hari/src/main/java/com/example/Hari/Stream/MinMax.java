package com.example.Hari.Stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MinMax {
    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Optional<Integer> minValue = values.stream().min(Comparator.naturalOrder());
        Optional<Integer> maxValue = values.stream().max(Comparator.naturalOrder());
        System.out.println("Minimum Value :" + minValue + "MaximumValue:" + maxValue);
    }
}
