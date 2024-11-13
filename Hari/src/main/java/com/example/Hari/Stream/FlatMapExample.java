package com.example.Hari.Stream;

import java.util.*;

public class FlatMapExample {
    public static void main(String[] args) {
        List<List<String>> values = Arrays.asList(Arrays.asList("a", "n"), Arrays.asList("p", "o"));

//        values.
//                stream().
//                flatMap(value -> value.
//                        stream()).
//                map(val -> val.
//                        toUpperCase(Locale.ROOT)).
//                forEach(System.out::println);


        values.stream().forEach(System.out::println);

        values.stream().
                flatMap(Collection::stream).
                map(String::toUpperCase).
                forEach(System.out::println);
    }
}
