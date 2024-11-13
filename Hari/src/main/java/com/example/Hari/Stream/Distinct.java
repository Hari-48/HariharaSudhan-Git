package com.example.Hari.Stream;

import java.util.Arrays;
import java.util.List;

public class Distinct {
    public static void main(String[] args) {
        List<String> letters = Arrays.asList("a","a","h","a","r","i","h","a","r","a","s","u","d","h","a","n");
        letters.stream().distinct().forEach(System.out::println);
    }
}
