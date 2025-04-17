package com.example.Hari.LeetCode.JavaNew;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaximumProductSubArray {
    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        System.out.println(maxProduct(nums));
    }
    public static  int maxProduct(int[] nums) {
        int curMin = nums[0];
        int curMax =nums[0];
        int max = nums[0];
        for(int i =1; i<nums.length;i++){
            int tempMax = Math.max(nums[i], Math.max(curMax * nums[i], curMin * nums[i]));
            curMin = Math.min(nums[i], Math.min(curMax * nums[i], curMin * nums[i]));
            curMax = tempMax;
            max = Math.max(max,curMax);

            log.info("{}->{},{},{}",nums[i],curMin,curMax,max);

        }
        return max;




    }



}
