package com.example.Hari.Stream;

import java.util.Arrays;

public class ParallelAndSequentialStreams {
    public static void main(String[] args) {
        int[] intArray=new int[1000000];
        Arrays.fill(intArray,2);

        System.out.println(intArray);
        long startTime = System.currentTimeMillis();

        long sequentialSum = Arrays.
                stream(intArray).
                map(n->n*n).
                sum();
        long endTime = System.currentTimeMillis();

        System.out.println("Sequential sum of factorial:" + sequentialSum);

        System.out.println("time taken by Sequential Streams:" + (endTime - startTime) + "ms");

        // Calculate sum of factorial using sequential streams
        startTime = System.currentTimeMillis();

        sequentialSum = Arrays.stream(intArray).
                parallel().
                map(n->n*n).
                sum();

        endTime = System.currentTimeMillis();

        System.out.println("Sequential sum of factorial:" + sequentialSum);
        System.out.println("time taken by Parallel Streams:" + (endTime - startTime) + "ms");

    }
}
