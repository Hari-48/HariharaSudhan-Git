package com.example.Hari.LeetCode.Java;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Temp {

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
//        int[] answer = new int[temp.length]; // Initialize array with size equal to temp.length
//
//        // Iterate through each temperature
//        for (int i = 0; i < temp.length; i++) {
//            int days = 0;
//
//            // Find the next warmer temperature
//            for (int j = i + 1; j < temp.length; j++) {
//                days++;
//                if (temp[j] > temp[i]) {
//                    answer[i] = days; // Store the days in the array
//                    break;
//                }
//            }
//
//            // If no warmer day is found, answer[i] remains 0 (default value for int arrays)
//        }
//
//        System.out.println(Arrays.toString(answer)); // Print the result
//    }
        int n = temperatures.length;
        int[] answer = new int[n];

        for (int i = n - 2; i >= 0; i--) {
            int next = i + 1;
            while (next < n && temperatures[i] >= temperatures[next]) {
                if (answer[next] == 0) break; // No warmer day ahead
                next += answer[next];        // Skip ahead using jump table
            }
            if (next < n && temperatures[i] < temperatures[next]) {
                answer[i] = next - i; // Calculate days to the next warmer day
            }
        }
        System.out.println(Arrays.toString(answer));

        //return answer;
    }
}
