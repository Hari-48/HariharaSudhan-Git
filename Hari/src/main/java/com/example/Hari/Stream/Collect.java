package com.example.Hari.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Collect {
    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
      List<Integer> square =  values.
              stream().
              filter(v->v%2==0).
              map(v->v*v).
              collect(Collectors.toList());
        square.forEach(System.out::println);
        System.out.println(square
        );
    }
}
