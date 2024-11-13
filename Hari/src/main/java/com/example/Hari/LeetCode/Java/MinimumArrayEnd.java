package com.example.Hari.LeetCode.Java;

import java.util.*;

public class MinimumArrayEnd {

    public static void main(String[] args) {

//
//        int n = 2;
//        int x = 7;


        int n = 6715154;
        int x = 7193485;

        int[] nums = new int[n];

        nums[0] = x;
        for (int i = 1; i < n; i++) {








            int y = nums[i - 1] + 1;
            while ((y & x) != x) {  // Check if the AND with x still results in x
                y++;
            }
            nums[i] = y;
        }

        System.out.println(Arrays.toString(nums));



        System.out.println(nums[n-1]);


    }

    private static int findNextNumber(int previous, int x) {
        int next = previous + 1;
        while ((next & x) != x) {  // Check if the AND with x still results in x
            next++;
        }
        return next;
    }


}
