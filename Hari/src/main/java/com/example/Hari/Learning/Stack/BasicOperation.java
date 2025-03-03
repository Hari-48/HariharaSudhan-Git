package com.example.Hari.Learning.Stack;

import java.util.Stack;

public class BasicOperation {

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack_push(stack);
        stack_pop(stack);
        stack_push(stack);
        stack_peek(stack);
        stack_search(stack, 20);
        stack_search(stack, 9);

    }

    private static void stack_pop(Stack<Integer> stack) {
        System.out.println("Pop Operation");
        for(int i=0;i<10;i++){
            Integer x = stack.pop();
            System.out.println(x);
        }
    }

    private static void stack_push(Stack<Integer> stack) {
        for(int i =0 ; i<10 ;i++){
            stack.push(i);
        }
        System.out.println(stack);
    }

    static void stack_peek(Stack<Integer> stack)
    {
        Integer element =  stack.peek();
        System.out.println("Element on stack top: " + element);
    }

    static void stack_search(Stack<Integer> stack, int element)
    {
        Integer pos = (Integer) stack.search(element);

        if(pos == -1)
            System.out.println("Element not found");
        else
            System.out.println("Element is found at position: " + pos);
    }


}
