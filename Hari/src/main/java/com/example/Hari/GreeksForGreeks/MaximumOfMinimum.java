package com.example.Hari.GreeksForGreeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class MaximumOfMinimum {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 50, 10, 70, 30};
        System.out.println(maxOfMins(arr));
    }


        public static ArrayList<Integer> maxOfMins(int[] arr) {
            int n = arr.length;
            ArrayList<Integer> result = new ArrayList<>();

            // Find the next smaller element index to the left
            int[] left = new int[n];
            int[] right = new int[n];
            Stack<Integer> stack = new Stack<>();

            // Initialize left limits
            for (int i = 0; i < n; i++) {
                while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                    stack.pop();
                }
                left[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }

            System.out.println(Arrays.toString(left));
            stack.clear();

            // Initialize right limits
            for (int i = n - 1; i >= 0; i--) {
                while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                    stack.pop();
                }
                right[i] = stack.isEmpty() ? n : stack.peek();
                stack.push(i);
            }


            System.out.println(Arrays.toString(right));


            // Store the min for each window size
            int[] res = new int[n + 1];
            Arrays.fill(res, Integer.MIN_VALUE);

            for (int i = 0; i < n; i++) {
                int length = right[i] - left[i] - 1;
                res[length] = Math.max(res[length], arr[i]);
            }
            System.out.println(Arrays.toString(res));


            // Fill in missing values
            for (int i = n - 1; i >= 1; i--) {
                res[i] = Math.max(res[i], res[i + 1]);
            }

            // Convert array to list
            for (int i = 1; i <= n; i++) {
                result.add(res[i]);
            }

            return result;
        }
    }

