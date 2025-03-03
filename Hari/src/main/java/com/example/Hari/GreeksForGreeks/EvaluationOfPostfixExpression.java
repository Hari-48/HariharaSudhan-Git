package com.example.Hari.GreeksForGreeks;

import java.util.*;

public class EvaluationOfPostfixExpression {

    public static void main(String[] args) {

            String[] arr = {"100", "200", "+", "2", "/", "5", "*", "7", "+"};
            System.out.println(evaluate(arr)); // Expected output: 757
        }

        public static int evaluate(String[] arr) {
            Stack<Integer> stack = new Stack<>();

            for (String token : arr) {
                if (isOperator(token)) {
                    int b = stack.pop(); // Second operand
                    int a = stack.pop(); // First operand
                    stack.push(applyOperation(a, b, token));
                } else {
                    stack.push(Integer.parseInt(token));
                }
            }
            return stack.pop(); // Final result
        }

        private static boolean isOperator(String s) {
            return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
        }
        private static int applyOperation(int a, int b, String operator) {
            switch (operator) {
                case "+": return a + b;
                case "-": return a - b;
                case "*": return a * b;
                case "/": return a / b;
                default: throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
}
