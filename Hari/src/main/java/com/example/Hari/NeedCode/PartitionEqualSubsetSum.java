package com.example.Hari.NeedCode;

import java.util.Arrays;
import java.util.stream.Stream;

public class PartitionEqualSubsetSum {

   /*       you are given an array of positive integers nums.

             Return true if you can partition the array into two subsets,
             subset1 and subset2 where sum(subset1) == sum(subset2).
             Otherwise, return false.

 */


    public static void main(String[] args) {

        int[] nums = {1, 2, 3,};
        boolean res = canPartition(nums);
        System.out.println(res);

    }


    public static boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }

        return dfs(nums, 0, sum / 2);
    }

    public static boolean dfs(int[] nums, int i, int target) {
        if (i == nums.length) {
            return target == 0;
        }
        if (target < 0) {
            return false;
        }

        return dfs(nums, i + 1, target) ||
                dfs(nums, i + 1, target - nums[i]);
    }


}
