package com.example.Hari.Stream;

import java.util.Arrays;
import java.util.List;

public class ReduceExample {
    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        //Integer sum = values.stream().reduce(0,(a,b)->a+b);
        Integer sum = values.stream().reduce(0,(Integer::sum));

        System.out.println(sum);
        Integer min = values.stream().reduce(0,(Integer::min));
        System.out.println(min);

    }
}
