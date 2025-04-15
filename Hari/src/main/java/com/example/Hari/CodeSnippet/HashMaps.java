package com.example.Hari.CodeSnippet;


import java.util.HashMap;
import java.util.Map;

public class HashMaps {
    public static void main(String[] args) {




         int a = 10 ;
         String b = "20";

        System.out.println(a+b);

        String text = "apple banana apple orange banana apple guava";
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : text.split(" ")) {


            /*
             *   If word is not in the map, it inserts word -> 1.
             *     If word already exists, it applies Integer::sum, which adds 1 to the existing count.
             */

            wordCount.merge(word, 1, Integer::sum);

            /*
             *  getOrDefault(word, 0) retrieves the current count of word in wordCount. If word is not in the map, it defaults to 0.
             * +1 increments the count.
             *  put(word, newCount) updates the map with the new count
             */
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);


            wordCount.computeIfAbsent("Mango", key -> 1);
            wordCount.computeIfPresent("Mango", (key, count) -> count + 1);


        }
        System.out.println(wordCount);
        //{banana=2, orange=1, apple=3, guava=1}


// Spring Allows Null value as key .
        Map<String, String> map = new HashMap<>();
        map.put(null, "hari");
        map.put(null, "Sudhan");
//        System.out.println(map);


    }


}
