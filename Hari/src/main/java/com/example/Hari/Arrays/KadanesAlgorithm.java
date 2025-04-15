package com.example.Hari.Arrays;

import java.util.Scanner;

public class KadanesAlgorithm {


//    public static void main(String[] args) {
//        int[]  arr = {2,3,-8,7,-1,2,3};
//        System.out.println( maxSumOfArray(arr));
//    }

    public static int maxSumOfArray(int[] arr) {

//
//        Scanner sc= new Scanner(System.in);
//
//        System.out.println("Enter the ArrayLissst (space-separated integers) and continues number:");
//        String input = sc.nextLine();
//
//
        int max=arr[0];
        int res=arr[0];
        for(int i=1 ; i<arr.length;i++){
            max=Math.max(max+arr[i],arr[i]);
            res = Math.max(res,max);
        }
        return res;
    }

    public static Object maxSumOfArray() {


        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the ArrayLissst (space-separated integers) and continues number:");
        String input = sc.nextLine();

        String[] inputArray = input.split(" ");
        int[] arr = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            arr[i] = Integer.parseInt(inputArray[i]);
        }
        int max=arr[0];
        int res=arr[0];
        for(int i=1 ; i<arr.length;i++){
            max=Math.max(max+arr[i],arr[i]);
            res = Math.max(res,max);
        }
        return res;

    }
}
