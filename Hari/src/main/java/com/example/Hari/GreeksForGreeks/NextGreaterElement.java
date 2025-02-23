package com.example.Hari.GreeksForGreeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class NextGreaterElement {

    public static void main(String[] args) {
        //   int[] arr = {10, 20, 30, 50};


        int[] arr = {41, 88, 58, 69, 93, 42, 44, 25, 12, 47, 41, 88, 58, 69, 93, 42, 44, 25, 12, 47};
        System.out.println(nextLargerElement(arr));

        //88 93 69 93 -1 44 47 47 47 88 88 93 69 93 -1 44 47 47 47 -1

    }

    public static ArrayList<Integer> nextLargerElement(int[] arr) {


        ArrayList<Integer> res = new ArrayList<>(Collections.nCopies(arr.length, -1));

        System.out.println(res);



        Stack<Integer> stack = new Stack<>();



        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                res.set(stack.pop(), arr[i]);
            }
            stack.push(i);
        }
        return res;


//        ArrayList<Integer> res = new ArrayList<>();
//        for (int i = 0; i < arr.length; i++) {
//
//            if (i == arr.length - 1) {
//                res.add(-1);
//                break;
//            }
//            for (int j = i; j < arr.length-1; j++) {
//                if (arr[i] < arr[j+1]) {
//                    res.add(arr[i + 1]);
//
//                } else {
//                    res.add(-1);
//
//                }
//            }
//
//        }
//        return res;
//    }


    }
}