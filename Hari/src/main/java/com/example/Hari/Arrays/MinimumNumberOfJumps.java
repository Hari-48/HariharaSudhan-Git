package com.example.Hari.Arrays;

public class MinimumNumberOfJumps {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        System.out.println(minJumps(arr));
    }


    static int minJumps(int[] arr) {
        int n = arr.length;

        // If the array has only one element, no jumps are needed
        if (n == 1) return 0;

        // If the first element is 0, we can't make any move
        if (arr[0] == 0) return -1;

        int maxReach = arr[0]; // The farthest we can reach
        int steps = arr[0];    // Steps left in the current jump
        int jumps = 1;         // Number of jumps taken

        for (int i = 1; i < n; i++) {
            // If we reach the last index
            if (i == n - 1) return jumps;

            maxReach = Math.max(maxReach, i + arr[i]); // Update maxReach
            steps--; // Use a step

            if (steps == 0) { // Need to take a jump
                jumps++;
                if (i >= maxReach) return -1; // If we can't go further
                steps = maxReach - i;
            }
        }

        return -1; // If end is never reached
    }


}
