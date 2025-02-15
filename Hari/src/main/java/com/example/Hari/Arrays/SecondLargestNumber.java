package com.example.Hari.Arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SecondLargestNumber {


    public static void main(String[] args) {
        int[] arr = {12, 35, 1, 10, 34, 1};
        System.out.println(secondLargestNumber(arr));
    }

    private static int secondLargestNumber(int[] arr) {
        int firstLargestNumber = Integer.MIN_VALUE;
        int secondLargestNumber = Integer.MIN_VALUE;
        for(int num : arr){
            if(num>firstLargestNumber){
                secondLargestNumber = firstLargestNumber;
                firstLargestNumber = num;
            }
             else if (num > secondLargestNumber && num != firstLargestNumber) {
                secondLargestNumber = num;
            }
        }
        return secondLargestNumber;
    }



    public static int secondLargestNumber() {

        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the ArrayList (space-separated integers) and continues number:");
        String input = sc.nextLine();

        String[] inputArray = input.split(" ");
        int[] arr = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            arr[i] = Integer.parseInt(inputArray[i]);
        }



        int firstLargestNumber = Integer.MIN_VALUE;
        int secondLargestNumber = Integer.MIN_VALUE;
        for(int num : arr){
            if(num>firstLargestNumber){
                secondLargestNumber = firstLargestNumber;
                firstLargestNumber = num;
            }
            else if (num > secondLargestNumber && num != firstLargestNumber) {
                secondLargestNumber = num;
            }
        }
        return secondLargestNumber;
    }


//    private static int secondLargestNumber(int[] arr) {
//        for (int j = 0; j < arr.length - 1; j++) {
//            for (int i = 0; i < arr.length - 1; i++) {
//                if (arr[i] < arr[i + 1]) {
//                    int temp = arr[i];
//                    arr[i] = arr[i + 1];
//                    arr[i + 1] = temp;
//                }
//            }
//            System.out.println(Arrays.toString(arr));
//
//        }
//        Arrays.sort(arr);
//        for (int i = arr.length - 2; i >= 0; i--) {
//            if (arr[i] != arr[arr.length - 1]) {
//                return arr[i];  // First number different from the largest
//            }
//        }
//        throw new IllegalArgumentException("No second largest element found.");
//    }


}
