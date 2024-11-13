package com.example.Hari.LeetCode.Java;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShortestSubArrayWithOR {

    private static final Logger log = LoggerFactory.getLogger(ShortestSubArrayWithOR.class);

    public static void main(String[] args) {
        int[] nums = {1,2,32,21};
        int k = 55;
        int res = minimumSubarrayLength(nums, k);
        System.out.println(res);
    }


        public static int minimumSubarrayLength(int[] nums, int k) {

            // If k is 0, return 1 because any non-empty subarray will have OR >= 0
            if (k == 0) return 1;

            int minLength = Integer.MAX_VALUE;
            int orResult = 0;
            int left = 0;

            // Iterate through the array
            for (int right = 0; right < nums.length; right++) {
                // Expand the window by including nums[right]
                orResult |= nums[right];

                // Shrink the window from the left as long as the condition is satisfied
                while (orResult >= k) {
                   log.info("=");
                    // Update the minimum length of subarray found
                    minLength = Math.min(minLength, right - left + 1);

                    // Shrink the window by moving the left pointer and updating the OR result
                    orResult ^= nums[left];
                    log.info(String.valueOf(orResult));
                    left++;

                    // No need for the check `if (orResult < k)` because the loop already breaks when `orResult` drops below `k`
                }
            }

            // Return the minimum length found, or -1 if no valid subarray exists
            return minLength == Integer.MAX_VALUE ? -1 : minLength;
        }












        public int minimumSubarrayLengths(int[] nums, int k) {
            int minLength = Integer.MAX_VALUE;
            int windowStart = 0;
            int windowEnd = 0;
            int[] bitCounts = new int[32]; // Tracks count of set bits at each position

            // Expand window until end of array
            while (windowEnd < nums.length) {
                // Add current number to window
                updateBitCounts(bitCounts, nums[windowEnd], 1);

                // Contract window while OR value is valid
                while (
                        windowStart <= windowEnd &&
                                convertBitCountsToNumber(bitCounts) >= k
                ) {
                    // Update minimum length found so far
                    minLength = Math.min(minLength, windowEnd - windowStart + 1);

                    // Remove leftmost number and shrink window
                    updateBitCounts(bitCounts, nums[windowStart], -1);
                    windowStart++;
                }

                windowEnd++;
            }

            return minLength == Integer.MAX_VALUE ? -1 : minLength;
        }

        // Updates bit count array when adding/removing a number from window
        private void updateBitCounts(int[] bitCounts, int number, int delta) {
            for (int bitPosition = 0; bitPosition < 32; bitPosition++) {
                // Check if bit is set at current position
                if (((number >> bitPosition) & 1) != 0) {
                    bitCounts[bitPosition] += delta;
                }
            }
        }

        // Converts bit count array back to number using OR operation
        private int convertBitCountsToNumber(int[] bitCounts) {
            int result = 0;
            for (int bitPosition = 0; bitPosition < 32; bitPosition++) {
                if (bitCounts[bitPosition] != 0) {
                    result |= 1 << bitPosition;
                }
            }
            return result;
        }
    }























//    In binary, XOR operates on each bit position independently:
//
//            1 ^ 1 = 0
//            0 ^ 0 = 0
//            1 ^ 0 = 1
//            0 ^ 1 = 1


//         0101   (orResult: 5 in binary)
//            ^ 0011   (toRemove: 3 in binary)
//            --------
//            0110   (new orResult: 6 in binary)




//    Bitwise OR (|): This operation combines the bits of numbers. If any bit in the range of numbers is 1, the result for that bit position is 1. For example:
//            3 | 5 results in 7 because 3 in binary is 011 and 5 is 101, so 011 | 101 = 111 (which is 7).



