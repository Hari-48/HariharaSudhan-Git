package com.example.Hari.LeetCode.JavaNew;

import java.util.ArrayList;

public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums ={1,2,3};
        System.out.println(containsDuplicate(nums));
    }

   static Boolean containsDuplicate(int[] nums) {

       ArrayList<Integer> result = new ArrayList<>();
        for(int num : nums){
            if(!result.contains(num)){
                result.add(num);
            }
            else {
                return true;
            }
        }
        return false;
    }




//    HashSet<Integer> seen = new HashSet<>();
//        for (int num : nums) {
//        if (seen.contains(num)) {
//            return true;
//        }
//        seen.add(num);
//    }
//        return false;









}
