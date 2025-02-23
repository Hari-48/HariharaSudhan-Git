package com.example.Hari.GreeksForGreeks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MedianOfArray {


    public static void main(String[] args) {
        int[] arr = {5, 15, 1, 3, 2, 8};

        System.out.println(getMedian(arr));
    }


    public static ArrayList<Double> getMedian(int[] arr) {


        ArrayList<Double> res = new ArrayList<>();
        ArrayList<Double> temp = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            temp.add((double) arr[i]);

            // Sort a copy of temp
            List<Double> sortedArray = new ArrayList<>(temp);
            Collections.sort(sortedArray);

            int n = sortedArray.size();

            // If odd, take the middle element
            if (n % 2 != 0) {
                res.add(sortedArray.get(n / 2));
            }
            // If even, take the average of two middle elements
            else {
                double median = (sortedArray.get(n / 2 - 1) + sortedArray.get(n / 2)) / 2.0;
                res.add(median);
            }
        }

        return res;
    }

    public static ArrayList<Double> getMedians(int[] arr) {
    ArrayList<Double> res = new ArrayList<>();

    // Max heap for the left half (smaller values)
    PriorityQueue<Integer> leftHalf = new PriorityQueue<>(Collections.reverseOrder());

    // Min heap for the right half (larger values)
    PriorityQueue<Integer> rightHalf = new PriorityQueue<>();

        for (int num : arr) {
        // Insert into appropriate heap
        if (leftHalf.isEmpty() || num <= leftHalf.peek()) {
            leftHalf.add(num);
        } else {
            rightHalf.add(num);
        }

        // Balance the heaps
        if (leftHalf.size() > rightHalf.size() + 1) {
            rightHalf.add(leftHalf.poll());
        } else if (rightHalf.size() > leftHalf.size()) {
            leftHalf.add(rightHalf.poll());
        }

        // Compute median
        if (leftHalf.size() == rightHalf.size()) {
            res.add((leftHalf.peek() + rightHalf.peek()) / 2.0);
        } else {
            res.add((double) leftHalf.peek());
        }
    }

        return res;
}
}











//        ArrayList<Double> res = new ArrayList<>();
//        ArrayList<Double> temp = new ArrayList<>();
//
//        int size = arr.length;
//
//
//        for (int i = 0; i < size; i++) {

//            temp.add((double) arr[i]);
//            System.out.println("temp  "+temp);
//
//            List<Double> sortedArray =temp.stream().sorted().toList();
//            System.out.println("after sorting :"+sortedArray);
//            if (i % 2 == 0) {
//
//                int size1 = (int) Math.round(sortedArray.size() / 2.0);
//
//                int size2 = +size1;
//                int t = size1 / size2;
//
//                res.add((double) t);
//
//
////                ..temp.stream().
//
//            } else {
//                int newSize = (int) Math.round(sortedArray.size() / 2.0);
//                res.add(temp.get(newSize));
//            }


//        }
//        System.out.println(res);
//
//        return res;
//
//        // code here
//    }
//}
