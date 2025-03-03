package com.example.Hari.GreeksForGreeks;

import java.util.Comparator;
import java.util.Optional;
import java.util.Stack;

public class GetMinFromStack {


    static Stack<Integer> stack = new Stack<>();

    static Stack<Integer> minStack = new Stack<>();

    public static void main(String[] args) {
//        push(0);
        push(30);
        push(10);
        push(4);
        push(40);
        push(50);

        System.out.println(stack);
        System.out.println("After pop Operation");
        pop();
        System.out.println(stack);
        System.out.println("Peek:" + peek());
        System.out.println("Min : " + getMin());


    }


    // Add an element to the top of Stack
    public static void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x<=minStack.peek()){
            minStack.push(x);

            System.out.println("newMin stack : "+ minStack);

        }

    }


    // Remove the top element from the Stack
    public static void pop() {
        stack.pop();
    }

    // Returns top element of the Stack
    public static int peek() {

        if (stack.isEmpty())
            return -1;
        return stack.peek();
    }

    //
//    // Finds minimum element of Stack
    public static int getMin() {
//        if (stack.isEmpty()) {
//            return -1;  // Handle empty stack case
//        }
//        int min = Integer.MAX_VALUE;
//        for (int num : stack) {  // Use an enhanced for loop to avoid modifying the stack
//            min = Math.min(min, num);
//        }
//        return min;

        if (minStack.isEmpty()) {
            return -1;  // Handle empty stack case
        }
       return minStack.peek();




    }

}









//
//class Solution {
//    Stack<Integer> stack;
//    Stack<Integer> minStack;
//
//    public Solution() {
//        stack = new Stack<>();
//        minStack = new Stack<>();
//    }
//
//    // Push an element onto the stack
//    public void push(int x) {
//        stack.push(x);
//        if (minStack.isEmpty() || x <= minStack.peek()) {
//            minStack.push(x); // Push only if it's a new minimum
//        }
//    }
//
//    // Remove the top element
//    public void pop() {
//        if (!stack.isEmpty()) {
//            int removed = stack.pop();
//            if (!minStack.isEmpty() && removed == minStack.peek()) {
//                minStack.pop(); // Remove from minStack if it was the minimum
//            }
//        }
//    }
//
//    // Get the top element
//    public int peek() {
//        if (stack.isEmpty()) return -1;
//        return stack.peek();
//    }
//
//    // Get the minimum element in O(1) time
//    public int getMin() {
//        if (minStack.isEmpty()) return -1;
//        return minStack.peek();
//    }
//}






















