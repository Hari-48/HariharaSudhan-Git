package com.example.Hari.LeetCode.JavaNew;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductofArrayExceptSelf {


    public static void main(String[] args) {
        int nums[] = {1, 2, 3, 4};
        System.out.println(productExceptSelf(nums));
    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }

        System.out.println(Arrays.toString(left));

        right[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }

        System.out.println(Arrays.toString(right));

        for (int i = 0; i < n; i++) {
            result[i]=(left[i] * right[i]);
        }



        return result;






    }



}
