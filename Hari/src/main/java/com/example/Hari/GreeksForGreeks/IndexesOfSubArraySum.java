package com.example.Hari.GreeksForGreeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class IndexesOfSubArraySum {


    public static void main(String[] args) {


        int[] array = {12,18,5,11,30,5};
        int target = 69;

        ArrayList<Integer> res = getResult(array, target);
        System.out.println("Answer"+res);


    }


//    public static ArrayList<Integer> getResult(int[] array, int target) {
//
//
//        ArrayList<Integer> newArray = new ArrayList((int) Arrays.stream(array).count());
//        ArrayList<Integer> results = new ArrayList<>();
//        for (int i = 0; i < array.length; i++) {
//            newArray.add(array[i]);
//            for (int j = i + 1; j < array.length; j++) {
//                int result = newArray.stream().mapToInt(Integer::intValue).sum();
//                if (result <= target) {
//                    if (result == target) {
//                        results.add(i + 1);
//                        results.add(j);
//                        return results;
//                    }
//                } else {
//                    newArray.clear();
//                    break;
//                }
//                newArray.add(array[j]);
//            }
//        }
//
//        results.add(-1);
//        return results;
//    }

    public static ArrayList<Integer> getResult(int[] array, int target) {
        ArrayList<Integer> results = new ArrayList<>();
        int len = array.length;
        for (int i = 0; i < len; i++) {
            int sum = 0;
            for (int j = i; j < len; j++) {
                sum += array[j];
                if (sum == target) {
                    results.add(i + 1); // Adding 1-based index for start
                    results.add(j + 1); // Adding 1-based index for end
                    return results; // Return immediately when target is found
                }
                if (sum > target) {
                    break; // Exit the inner loop early if the sum exceeds the target
                }
            }
        }
        // If no subarray matches the target, return [-1]
        results.add(-1);
        return results;
    }



}
