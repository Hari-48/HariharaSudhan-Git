package com.example.Hari.Stream;

import java.util.ArrayList;
import java.util.List;

public class Skip {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0 ; i<100;i++){
            numbers.add(i);
        }
        System.out.print(numbers);
        List<Integer> afterSkip = numbers.stream().skip(25).toList();
        System.out.print(afterSkip);
    }
}
