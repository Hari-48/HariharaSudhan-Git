package com.example.Hari.Stream;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestSpeed {

    public static long forEachLoop(List<String> names){
        long totalCount = 0;
        for(String name : names){
            if(name.length()>5){
                totalCount = totalCount+name.length();
            }
        }
        System.out.println(totalCount);
        return totalCount;

    }

    public static long stream(List<String> names){
        long totalCount = names.stream().mapToLong(String::length).filter(length->length>5).sum();
        System.out.println(totalCount);
        return  totalCount;
    }

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < 50000000; i++) {
            names.add(generateList());
        }

        forEachLoop(names);
        stream(names);

        long start1 = System.currentTimeMillis();
        long count1 = forEachLoop(names);
        long end1 = System.currentTimeMillis();

        System.out.println("For-each loop result: " + count1);
        System.out.println("For-each loop time: " + (end1 - start1) + " ms");

        // Stream timing
        long start2 = System.currentTimeMillis();
        long count2 = stream(names);
        long end2 = System.currentTimeMillis();

        System.out.println("Stream result: " + count2);
        System.out.println("Stream time: " + (end2 - start2) + " ms");
    }

//    TotalTime : 172  seconds



    private static final  String[] FIRST_NAME = {"virat","king","raina","suresh","dhoni"};
    private static  final String[] LAST_NAME = {"warner","smith","clark","finch","don"};

    private static String generateList() {

        Random random = new Random();
        String firstName = FIRST_NAME[random.nextInt(FIRST_NAME.length)];
        String lastName = LAST_NAME[random.nextInt(LAST_NAME.length)];
        return  firstName+"_"+lastName;

    }


}
