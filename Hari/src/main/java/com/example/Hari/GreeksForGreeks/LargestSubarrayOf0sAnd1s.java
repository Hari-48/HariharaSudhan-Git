package com.example.Hari.GreeksForGreeks;

import java.util.*;
import java.util.stream.Collectors;

public class LargestSubarrayOf0sAnd1s {

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 0};
        Integer result = maxLen(arr);
        System.out.println(result);
    }

    public static int maxLen(int[] arr) {
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int longestSubArray = 0;

        for (int i = 0; i < arr.length; i++) {
            // Replace 0 with -1 to handle the problem
            sum += arr[i] == 0 ? -1 : 1;

            // If sum is 0, update longestSubArray
            if (sum == 0) {
                longestSubArray = i + 1;
            } else if (map.containsKey(sum)) {
                // If sum exists in the map, calculate subarray length
                longestSubArray = Math.max(longestSubArray, i - map.get(sum));
            } else {
                // Store sum and its index if not already in the map
                map.put(sum, i);
            }
        }
        return longestSubArray;
    }
}
