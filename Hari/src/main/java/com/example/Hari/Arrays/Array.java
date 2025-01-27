package com.example.Hari.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Scanner;

public class Array {
    public static void main(String[] args) {


        HashMap<Integer,String> questionMap = new HashMap<Integer,String>();
        questionMap.put(1,"Find Indexes of Sub array Sum ");
        questionMap.put(2,"Missing Elements in Array ");

        System.out.println(questionMap);

        System.out.println("--- CHOOSE THE QUESTION NUMBER ---");

        Scanner scanner = new Scanner(System.in);
        int questionNumber = scanner.nextInt();
        System.out.println(getAnswer(questionNumber));
    }

    public static Object getAnswer(int questionNumber) {
        switch (questionNumber) {
            case 1:
                return IndexOfSubArraySum.getIndexOfSubArraySum();

            case 2:
                return MissingElements.missingNumber();
            default:
                return new ResponseEntity<>("NOT FOUND ", HttpStatus.NOT_FOUND);
        }
    }
}
