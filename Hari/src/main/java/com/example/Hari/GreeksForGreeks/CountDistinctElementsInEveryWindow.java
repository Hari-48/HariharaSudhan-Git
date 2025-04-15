package com.example.Hari.GreeksForGreeks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class CountDistinctElementsInEveryWindow {
    public static void main(String[] args) {


        int[] array = {8,10,6};
        int k = 1;

        ArrayList<Integer> res = getDistinctCount(array, k);
        System.out.println("Answer" + res);


    }


    private static ArrayList<Integer> getDistinctCount(int[] arr, int k) {
        ArrayList<Integer> results = new ArrayList<>();
        if (k > arr.length) {
            System.out.println("Window size cannot be greater than array size.");
            return results;
        }

        // Traverse through the array with a sliding window of size `k`
        for (int i = 0; i <= arr.length - k; i++) {
            // Use a HashSet to store distinct elements in the current window
            HashSet<Integer> set = new HashSet<>();

            // Add elements of the current window to the set
            for (int j = i; j < i + k; j++) {
                set.add(arr[j]);
            }

            // Add the distinct count to the result
            results.add(set.size());
        }

        return results;
    }


//    private static ArrayLissst<Integer> getDistinctCount(int[] array, int k) {
//        ArrayLissst<Integer> results = new ArrayLissst<>();
//        for (int i = 0; i < k; i++) {
//            ArrayLissst<Integer> sublist = new ArrayLissst<>();
//            // Add `k` elements to the sublist starting from index `i`
//            for (int j = i; j < i + k; j++) {
//                sublist.add(array[j]);
//            }
//            results.add((int) sublist.stream().distinct().count());
//        }
//        return results;
//    }


}
