package com.example.Hari.GreeksForGreeks;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Parenthesis {

    public static void main(String[] args) {
//        String paran = "())()";

        String paran = ")()())";
        System.out.println(maxLength(paran));
    }

    static int maxLength(String s) {
        int maxLen = 0;
        int left = 0, right = 0;

        // Left to Right Pass
        for (char c : s.toCharArray()) {
            if (c == '(') left++;
            else right++;

            if (left == right) maxLen = Math.max(maxLen, 2 * right);
            else if (right > left) left = right = 0; // Reset if invalid
        }

        left = right = 0;

        // Right to Left Pass
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') right++;
            else left++;

            if (left == right) maxLen = Math.max(maxLen, 2 * left);
            else if (left > right) left = right = 0; // Reset if invalid
        }

        return maxLen;
    }
}











//        for (char ch : s.toCharArray()) {
//            if (ch == '(') {
//                stack.push(ch);
//            } else if (ch == ')' && !stack.isEmpty()) {
//                stack.pop();
//                result.append("()");
//            }
//        }
//        System.out.println( result.toString());
//


//        return count;
//        // code here
//    }



//}
