package com.example.Hari.Learning.Stack;

import java.util.Stack;

public class ParenthesisChecker {

    public static void main(String[] args) {

        String s = "[()()]{}";
        System.out.println(checker(s));

    }

    private static boolean checker(String s) {

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '{' || ch == '[' || ch == '(') {
                stack.push(ch);
            } else if (ch == '}' || ch == ']' || ch == ')') {
                if (stack.isEmpty()) {
                    return false;
                }


                char top = stack.pop();
                if (!isMatchingPair(top, ch))
                    return false;
            }
        }
        return stack.isEmpty();

    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }


}
