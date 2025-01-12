package com.example.Hari.GreeksForGreeks;

import java.util.ArrayList;
import java.util.Arrays;

public class PrintAnagramsTogether {

    public static void main(String[] args) {
       String  arr[] = {"act", "god", "cat", "dog", "tac"};
       ArrayList<ArrayList<String>> result = getAnagram(arr);
    }

    private static ArrayList<ArrayList<String>> getAnagram(String[] arr) {
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        for (String word : arr) {
            boolean added = false;
            for (ArrayList<String> group : ans) {
                char[] x = group.get(0).toCharArray();
                char[] y = word.toCharArray();
                Arrays.sort(x);
                Arrays.sort(y);
                if (Arrays.equals(x, y)) {
                    group.add(word);
                    added = true;
                    break;
                }
            }
            if (!added) {
                ArrayList<String> newGroup = new ArrayList<>();
                newGroup.add(word);
                ans.add(newGroup);
            }
        }
        return ans;
    }












//        for(int i = 0 ; i<arr.length;i++) {
//
//            if (i == 0) {
//                a.add(arr[i]);
//            } else {
//                System.out.println("_" + a.get(0));
//                System.out.println(arr[i]);
//
//                char[] x = a.get(0).toCharArray();
//                char[] y = arr[i].toCharArray();
//                Arrays.sort(x);
//                Arrays.sort(y);
//                if (Arrays.equals(x, y)) {
//                    a.add(arr[i]);
//                }
//            }
//            System.out.println(a);
//
//
//
//
//        }return ans;}


}
