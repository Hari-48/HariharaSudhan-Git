package com.example.Hari.GreeksForGreeks;

import java.util.*;

public class LongestSubstringWithDistinctCharacters {



    public static void main(String[] args) {


        String s = "abcdefabcbb";


        Integer res = getLongestDistinctCount(s);
        System.out.println("Answer" + res);


    }

    private static Integer getLongestDistinctCount(String s) {
        ArrayList<Integer> results = new ArrayList<>();
        HashSet<Character> characterHashSet = new HashSet<>();
        int start = 0;
        int maxLength = 0;
        int currentLength = 0;

        for (int i = 0 ; i<s.length(); i++){
            while (characterHashSet.contains(s.charAt(i))) {
                characterHashSet.remove(s.charAt(start));  // Remove the character at start
                start++;
            }

            characterHashSet.add(s.charAt(i));
            currentLength = i - start + 1;
            maxLength = Math.max(maxLength, currentLength);
        }

       return maxLength;
    }
}
