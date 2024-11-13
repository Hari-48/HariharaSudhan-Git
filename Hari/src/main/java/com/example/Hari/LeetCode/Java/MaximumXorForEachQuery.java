package com.example.Hari.LeetCode.Java;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MaximumXorForEachQuery {

    public static void main(String[] args) {

      //  int[] nums = {0, 1, 1, 3};

        int [] nums = {2,3,4,7};

        List<Integer> result = new ArrayList<>();
        int[] currentNums = nums;


        int maximumBit = 20;

        int maxiBit = (int) Math.pow(2, maximumBit) - 1;

        for (int i = nums.length; i > 0; i--) {
            int k = comulativeOfXOR(currentNums) ^ maxiBit;
            result.add(k);
            currentNums = removeLastElement(currentNums);
        }
        System.out.println(result);
    }

    private static int[] removeLastElement(int[] nums) {
        int[] newNums = new int[nums.length - 1];
        System.arraycopy(nums, 0, newNums, 0, nums.length - 1);
        return newNums;
    }

    private static int comulativeOfXOR(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        System.out.println(ans);
        return ans;
    }



    public int[] getMaximumXor(int[] nums, int maximumBit) {

        int n = nums.length;
        int xorr = nums[0];
        int maxXor = (1 << maximumBit) - 1;

        for (int i = 1; i < n; i++) {
            xorr ^= nums[i];
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = xorr ^ maxXor;
            xorr ^= nums[n - 1 - i];
        }

        return ans;


    }


}
