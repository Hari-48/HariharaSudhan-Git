package com.example.Hari.GreeksForGreeks;

import java.util.Arrays;

public class EquilibriumPoint {

    public static void main(String[] args) {
        int [] arr = {1,2,0,3};
        Integer result = findEquilibrium(arr);
        System.out.println(result);
    }

    public static int findEquilibrium(int arr[]) {
        int leftSum =0;
        int rightSum= Arrays.stream(arr).sum();
        for (int j=0;j<arr.length;j++) {
            rightSum = rightSum - arr[j];
            if (rightSum == leftSum) {
                return j;
            }
            leftSum=leftSum+arr[j];
        }
        return -1;








    }



}
