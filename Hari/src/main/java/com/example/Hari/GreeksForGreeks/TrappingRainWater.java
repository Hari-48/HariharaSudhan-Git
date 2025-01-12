package com.example.Hari.GreeksForGreeks;

import java.util.ArrayList;
import java.util.Stack;

public class TrappingRainWater {


    // find left max
    // find rightMax

    // loop each building c, compare lef and rightmax find min of them and sub with actual building






    public static void main(String[] args) {
        int arr[] = {3, 0, 1, 0, 4, 0, 2};
        int result = maxWater(arr);
        System.out.println(result);
    }

    public static int maxWater(int arr[]) {
        int total_water = 0;
        int leftMax = arr[0];

        Stack<Integer> rightMax = new Stack<>();

        rightMax.push(arr[arr.length-1]);

        for(int i=arr.length-2 ; i>=2; i--){
            int temp = Math.max(rightMax.peek(),arr[i]);
            rightMax.push(temp);
        }

        System.out.println(leftMax);
        System.out.println(rightMax);

        for(int i = 1 ; i<arr.length-1 ; i++){
            int minimum = Math.min(rightMax.peek(),leftMax);
            int temp = minimum-arr[i];
            total_water+=Math.max(0,temp);

            rightMax.pop();
            leftMax = Math.max(leftMax,arr[i]);
        }
        return total_water;


    }
}




