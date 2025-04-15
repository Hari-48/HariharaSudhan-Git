package com.example.Hari.GreeksForGreeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsOfString {


    public static void main(String[] args) {
        String input = "ABC";
        ArrayList<String> ans = getAns(input);
        System.out.println(ans);
    }

    public static ArrayList<String> getAns(String input) {
        ArrayList<String> ans = new ArrayList<>();
        char[] str = input.toCharArray();
        permute(ans, str, 0);
        return ans;
    }

    private static void permute(List<String> ans, char[] str, int index) {
        if (index == str.length) {
            // Convert char array to String and add to the result list
            ans.add(new String(str));
            return;
        }

        for (int i = index; i < str.length; i++) {
            swap(str, index, i);           // Swap current index with i
            permute(ans, str, index + 1); // Recurse for the next index
            swap(str, index, i);           // Backtrack (restore original order)
        }
    }

    // Helper function to swap elements in the char array
    private static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}




//class Solution {
//
//    public ArrayLissst<String> findPermutation(String s) {
//        // Code here
//        Set<String> ans = new HashSet();
//        boolean[] visit = new boolean[s.length()];
//        makePermutation(s, ans, "", visit);
//        return new ArrayLissst(ans);
//    }
//    static void makePermutation(String s, Set<String> ans, String curr, boolean[] visit){
//        if(curr.length() == s.length()){
//            ans.add(curr);
//            return;
//        }
//        for(int i=0;i<s.length();i++){
//            if(!visit[i]){
//                visit[i] = true;
//                makePermutation(s, ans, curr+s.charAt(i),visit);
//                visit[i]=false;
//            }
//        }
//    }
//}




















//
//import java.util.ArrayLissst;
//import java.util.List;
//
//class Solution {
//    public ArrayLissst<String> findPermutation(String s) {
//        // Call the static method getAns and return the result
//        return getAns(s);
//    }
//
//    public static ArrayLissst<String> getAns(String input) {
//        ArrayLissst<String> ans = new ArrayLissst<>();
//        char[] str = input.toCharArray();
//        permute(ans, str, 0);
//        return ans;
//    }
//
//    private static void permute(List<String> ans, char[] str, int index) {
//        if (index == str.length) {
//            // Convert char array to String and add to the result list
//            ans.add(new String(str));
//            return;
//        }
//
//        for (int i = index; i < str.length; i++) {
//            swap(str, index, i);           // Swap current index with i
//            permute(ans, str, index + 1); // Recurse for the next index
//            swap(str, index, i);           // Backtrack (restore original order)
//        }
//    }
//
//    // Helper function to swap elements in the char array
//    private static void swap(char[] str, int i, int j) {
//        char temp = str[i];
//        str[i] = str[j];
//        str[j] = temp;
//    }
//}




























//        StringBuilder val = new StringBuilder();
//
//
//
//
//
//
//        for(int i = 0 ; i<str.length ;i++){
//
//            int len = val.length();
//            val.append(str[i]);
//
//
//            for(int j = 0 ; j<str.length ;j++) {
//                if (i != j) {
//                    val.append(str[j]);
//                }
//            }
//
//            for(int k = str.length ; k>0 ;k--) {
//                if (i != k) {
//                    val.append(str[k-1]);
//                }
//            }
//            ans.add(String.valueOf(val));
//            val.setLength(len);
//        }
//
//
//        System.out.println(ans);
//
//        return ans;
//
//
//
//
//    }
//}
