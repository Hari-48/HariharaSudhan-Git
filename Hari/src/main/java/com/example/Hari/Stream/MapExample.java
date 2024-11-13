package com.example.Hari.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MapExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("hari", "abi", "ramya");
        names.stream().
                map(name -> name.
                        toUpperCase(Locale.ROOT)).
                forEach(System.out::println);

        names.stream().
                map(String::toUpperCase).
                forEach(System.out::println);

    }
}
