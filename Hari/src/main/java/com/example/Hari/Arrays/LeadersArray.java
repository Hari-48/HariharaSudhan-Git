package com.example.Hari.Arrays;

import java.util.ArrayList;
import java.util.Collections;

public class LeadersArray {

    public static void main(String[] args) {
//        ArrayLissst<Integer> arr = new ArrayLissst<>();
//
//
//
//
//        arr.add(16);
//        arr.add(17);
//        arr.add(4);
//        arr.add(3);
//        arr.add(5);
//        arr.add(2);



//        int[] arr = {16, 17, 4, 3, 5, 2};


        int[] arr = {30, 10, 10, 5};

        leaders(arr);



    }

    static ArrayList<Integer> leaders(int arr[]) {



        ArrayList<Integer> leader = new ArrayList<>();

        int n = arr.length;

        int maxRight = arr[n-1];
        leader.add(maxRight);


        for(int i = n-2 ; i>=0;i--){
            if(arr[i]>=maxRight){
                maxRight=arr[i];
                leader.add(maxRight);
            }
        }

        Collections.reverse(leader);

        System.out.println(leader);
        return leader;

//        System.out.println(leader);
//        return (ArrayLissst<Integer>) leader.reversed();





    }

}
