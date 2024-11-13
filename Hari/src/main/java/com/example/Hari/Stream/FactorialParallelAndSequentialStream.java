package com.example.Hari.Stream;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
@Slf4j
public class FactorialParallelAndSequentialStream {
    public static void main(String args[]) {

        long[] longArray = new long[4444];
        for (int i = 0; i < longArray.length; i++) {
            longArray[i] = i + 1;
        }

        // Calculate sum of factorial using sequential streams
        long startTime = System.currentTimeMillis();
        long sequentialSum = Arrays.
                stream(longArray).
                map(FactorialParallelAndSequentialStream::factorial).
                sum();
        long endTime = System.currentTimeMillis();

        System.out.println("Sequential sum of factorial : " + sequentialSum);
        System.out.println("time taken by Sequential Streams:" + (endTime - startTime) + "ms");

        // Calculate sum of factorial using sequential streams
        startTime = System.currentTimeMillis();
        sequentialSum = Arrays.stream(longArray).
                parallel().
                map(FactorialParallelAndSequentialStream::factorial).
                sum();
        endTime = System.currentTimeMillis();

        System.out.println("Sequential sum of factorial:" + sequentialSum);
        System.out.println("time taken by Parallel Streams:" + (endTime - startTime) + "ms");

    }
    public static long factorial(long number) {
        return LongStream.rangeClosed(1, number).reduce(1, (long a, long b) -> a * b);
    }

//    public static int newfactorial(int n) {
//        if (n == 0) {
//            return 1;
//        }
//        int result = 1;
//        for (int i = 1; i <= n; i++) {
//            result *= i;
//        }
//        return result;
//    }


}
