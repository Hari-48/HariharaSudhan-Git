package com.example.Hari.LeetCode.JavaNew;

public class MaximumSubArray {

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {

        int res = nums[0];
        int max = nums[0];
        for (int n = 1; n < nums.length; n++) {
            max=Math.max(max+nums[n],nums[n]);
            res = Math.max(res, max);
        }
        return res;


    }

}
