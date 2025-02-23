package com.example.Hari.GreeksForGreeks;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class KLargestElement {


    public static void main(String[] args) {
//        int[] arr = {12, 5, 787, 1, 23};

        int[] arr =  {6110 ,5340 ,30210 ,2271 ,12616 ,18751 ,28980 ,32632 ,306 ,31136};


        int k = 6;
        System.out.println(kLargest(arr, k));
    }

    private static ArrayList<Integer> kLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i : arr){
            pq.add(i);
            if(pq.size()>k){
                pq.poll();
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while (!pq.isEmpty()){
            ans.add(0, pq.poll());
        }
        return ans;

    }

//    public static ArrayList<Integer> kLargest(int[] arr, int k) {
//
//
//        ArrayList<Integer> result = new ArrayList<>();
//
//        int temp = 0;
//
//        for (int i = 0; i < arr.length - 1; i++) {
//            boolean swapped = false;
//
//            // Inner loop for comparing adjacent elements
//            for (int j = 0; j < arr.length - 1 - i; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                    swapped = true; // Track if swapping happened
//                }
//            }
//
//            // If no swapping happened, array is already sorted
//            if (!swapped) break;
//        }
//
//
//
//
//
//        for (int i = arr.length - 1; i >= k + 1; i--) {
//            result.add(arr[i]);
//        }
//
//        return result;
//
//        // Your code here
//    }
}
