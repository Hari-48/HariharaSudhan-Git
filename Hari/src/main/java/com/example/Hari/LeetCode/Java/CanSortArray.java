package com.example.Hari.LeetCode.Java;

import java.util.Arrays;

public class CanSortArray {

//
//2 in binary is 0000 0010
// 16 in binary is 0001 0000
//    When we perform a bitwise AND:
//

//   0000 0010  (binary representation of 2)
//&  0001 0000  (binary representation of 16)
//  -------------
//   0000 0000  (result is 0)
//    Since the result is 0, 2 and 16 do not share any common set bits.


    public static void main(String[] args) {
        int[] num = {8, 4, 2, 30, 15};
        System.out.println(canSortArray(num));
        //System.out.println(canSortBySwapping(nums));
    }

    public static boolean canSortArray(int[] nums) {
        // Avoid modifying the input directly
        int[] values = Arrays.copyOf(nums, nums.length);

        // Arrays.stream(values).forEach(System.out::println);

        int n = values.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (values[j] <= values[j + 1]) {
                    // No swap needed
                    continue;
                } else {
                    if (Integer.bitCount(values[j]) == Integer.bitCount(values[j + 1])) {
                        // Swap the elements
                        int temp = values[j];
                        values[j] = values[j + 1];
                        values[j + 1] = temp;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    // Function to check if the array can be sorted with allowed swaps
//    public static boolean canSortBySwapping(int[] nums) {
//        int n = nums.length;
//
//        // Bubble sort-like approach with condition-based swapping
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = 0; j < n - 1; j++) {
//                if (countSetBits(nums[j]) == countSetBits(nums[j + 1]) && nums[j] > nums[j + 1]) {
//                    // Swap nums[j] and nums[j+1] if they have the same set bits and are out of order
//                    int temp = nums[j];
//                    nums[j] = nums[j + 1];
//                    nums[j + 1] = temp;
//                }
//            }
//        }
//
//        // Check if the array is sorted
//        for (int i = 0; i < n - 1; i++) {
//            if (nums[i] > nums[i + 1]) {
//                return false;  // Not sorted
//            }
//        }
//        return true;  // Sorted
//    }
//
//    // Helper function to count the number of set bits in a number
//    public static int countSetBits(int num) {
//        int count = 0;
//        while (num > 0) {
//            count += (num & 1);
//            num >>= 1;
//        }
//        return count;
//    }
//}


/*
https://leetcode.com/problems/find-if-array-can-be-sorted/editorial
Algorithm
Get the length of the array, denoted as n.
Make a copy values of the array, to avoid modifying the input.
Loop with i from 0 to n-1.
Start an inner loop with j from 0 to n-i-1.
In each iteration of the inner loop, compare the values values[j] and values[j+1].
If values[j] <= values[j+1], no swap is needed; continue.
If values[j] > values[j+1], the elements must be swapped.
If the elements have the same number of set bits, swap them.
        Otherwise, return false.
If the outer loop ends without returning false, the array is sorted, so return true.
*/

}
