package com.example.Hari.Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class MissingElements {


//    public static void main(String[] args) {
//       int ans =  missingNumber();
//        System.out.println(ans);
//    }
    public static Object  missingNumber() {

        Scanner sc= new Scanner(System.in);

        System.out.println("Enter the ArrayLissst (space-separated integers) and continues number:");
        String input = sc.nextLine();

        String[] inputArray = input.split(" ");
        int[] arr = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            arr[i] = Integer.parseInt(inputArray[i]);
        }

        Arrays.sort(arr);
        int size = arr.length;
        for(int i = 1 ; i<=size;i++){
            if (i != arr[i-1]) {
                return "Missing element is " + i;
            }
        }
        return "Next value is "+ (size + 1);
    }


}
