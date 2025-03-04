package com.example.Hari.GreeksForGreeks;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] arr = {5, 8, 3, 7, 9, 1};
        System.out.println(lis(arr));


    }

    static int lis(int arr[]) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxLength = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]); // Track max LIS length
        }

        return maxLength;
    }


}
