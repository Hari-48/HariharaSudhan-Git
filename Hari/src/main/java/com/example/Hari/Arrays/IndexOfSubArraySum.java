package com.example.Hari.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Scanner;

@Slf4j
public class IndexOfSubArraySum {
    public static Object getIndexOfSubArraySum(){


        Scanner sc= new Scanner(System.in);

        System.out.println("Enter the ArrayList (space-separated integers):");
        String input = sc.nextLine();

        String[] inputArray = input.split(" ");
        int[] arr = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            arr[i] = Integer.parseInt(inputArray[i]);
        }


        System.out.println("Enter the Sum value:");
        int target = sc.nextInt();


        ArrayList<Integer> index = new ArrayList<>();
        for(int i= 0 ; i<arr.length ;i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == target) {
                    index.add(i + 1);
                    index.add(j + 1);
                    return index;
                }
                if (sum > target) {
                    break;
                }
            }
        }
        return "NOT FOUND";
    }

}
