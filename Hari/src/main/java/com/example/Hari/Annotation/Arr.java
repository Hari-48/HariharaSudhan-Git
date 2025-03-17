package com.example.Hari.Annotation;

import java.util.Arrays;
import java.util.List;

public class Arr {
    public static void main(String[] args) {
        arraysAsList();
        listOf();


    }

    private static void arraysAsList() {
        System.out.println("----USING Arrays.asList()-----");
        System.out.println("it supports null value ");
        String[] numbersArray = {"one", "two", "three", "four", null};
        System.out.println("Numbers Array->" + numbersArray);
        List<String> numbersList = Arrays.asList(numbersArray);
        System.out.println("Numbers List->" + numbersList);
        numbersList.set(1, "new two ");
        System.out.println("After modify the Data ->" + numbersList);
        System.out.println("It will reflect  in original array too->" + Arrays.toString(numbersArray));
        System.out.println("Adding one elements to List - not supported");
        // numbersList.add("five");
        //System.out.println(numbersList);
    }

    private static void listOf() {
        System.out.println("----------USING listOf-----------");
        System.out.println("it will not supports null value ");
        String[] numbersArray = {"one", "two", "three", "four"};
        System.out.println("Numbers Array->" + numbersArray);
        List<String> numbersList = List.of(numbersArray);
        System.out.println("Numbers List->" + numbersList);
        System.out.println("we cannot modify the data ");
        //numbersList.set(1,"new two ");
        System.out.println("Adding one elements to List - not supported");
        //numbersList.add("five");
    }


//    private static void arraysAsList() {
//        // Basic example of Arrays.asList()
//        String[] numbers = {"one", "two", "three", "four", null};
//
//        // Convert array to list
//        List<String> numbersList = Arrays.asList(numbers);
//
//        // Show we can change list values
//        numbersList.set(1, "new two");
//
//        // When we change the list, the original array also changes
//        System.out.println("Array after change: " + Arrays.toString(numbers));
//
//        // Important: Can't add new items to this list
//        // This would cause an error:
//        // numbersList.add("five");
//    }

//    private static void listOf() {
//        // Show how List.of() works
//        String[] numbersArray = {"one", "two", "three", "four"};
//
//        // Create an immutable list from the array
//        List<String> numbersList = List.of(numbersArray);
//
//        // Print the results
//        System.out.println("Original array: " + numbersArray);
//        System.out.println("Created list: " + numbersList);
//
//        // Important: This list cannot be changed
//        // These operations would cause errors:
//        // numbersList.set(1, "new two");  // Can't change items
//        // numbersList.add("five");        // Can't add items
//    }
//



}
