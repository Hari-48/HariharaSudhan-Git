package com.example.Hari.LeetCode.Java.ArrayANDString;

import java.util.stream.Stream;

public class MergeStringsAlternately {
    public static void main(String[] args) {
        mergeAlternately("abc", "pqrs");
    }


    public static String mergeAlternately(String word1, String word2) {

        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < word1.length() || j < word2.length()) {
            if (i < word1.length()) {
                stringBuilder.append(word1.charAt(i));
                i++;
            }
            if (j < word2.length()) {
                stringBuilder.append(word2.charAt(i));
                j++;
            }
        }
        System.out.println(stringBuilder);
        return String.valueOf(stringBuilder);

    }
}