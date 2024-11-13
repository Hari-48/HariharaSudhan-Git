package com.example.Hari.Stream;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConvertToStream {
    public static void main(String[] args){
        // Covert primitive - stream
        int[] primitiveArray ={1,2,3,4,5,6,7};
        IntStream intStream = Arrays.stream(primitiveArray);
        intStream.forEach(System.out::println);

        // Covert WrapperClass - stream
        Integer[] objectArray ={10,11,12,13,14,15,15};
        Stream<?> objStream=Stream.of(objectArray);

      //  Stream<?> objStream=Arrays.stream(objectArray);
        objStream.forEach(System.out::println);

        // Covert String or Object - stream
        String[] days ={"mon","tues","wed","thurs","frid","sat","sun"};
        Stream<?> dayStream=Stream.of(days);
        // Stream<?> dayStream = Arrays.stream(dlog.info("============:{}",number);ays);
        dayStream.forEach(System.out::println);
        //dayStream.forEach(day -> System.out.println(day));

        // Covert List - Stream
        List<Integer> dates = Arrays.asList(14,56,23,48);
        //dates.forEach(System.out::println);
        dates.stream().forEach(System.out::println);


        List<String> months = Arrays.asList("jan","feb","march","april");
        months.stream().forEach(System.out::println);

        List<Object> objects = new ArrayList<>(2);
        objects.add("Hari");
        objects.add(21);
        objects.stream().forEach(System.out::println);

        // Create a HashSet of Objects
        Set<Object> set = new HashSet<>();
        // Add elements to the set
        set.add("First Element");
        set.add("Second Element");
        // Print elements of the set
        set.forEach(System.out::println);

        // Create a HashMap of String keys and Object values
        Map<String, Object> map = new HashMap<>();
        // Add elements to the map
        map.put("First Key", "First Element");
        map.put("Second Key", "Second Element");
        map.entrySet().stream().forEach(System.out::println);

        // Print elements of the map
        map.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
