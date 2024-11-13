package com.example.Hari.LeetCode.Java;

import java.util.*;

public class DuplicateInteger {

    public static void main(String[] args) {
        int[] nums = {1,2,1,4,2};

        Set<Integer> uniqueNums = new HashSet<>();

        for(int num :nums){
            if(uniqueNums.add(num)){
                System.out.println(true);
            }
            else {
                System.out.println(false);
            }
        }
    }



//    public boolean hasDuplicate(int[] nums) {
//
//        Set<Integer> uniqueNums = new HashSet<>();
//
//        for (int num : nums) {
//
//            if (!uniqueNums.add(num)) {
//                return true;
//            }
//        }
//
//        return false;
//
//    }


}
