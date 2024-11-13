package com.example.Hari.LeetCode.Java;
// Nov - 04
public class RotateString {

//    Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
//
//    A shift on s consists of moving the leftmost character of s to the rightmost position.
//
//    For example, if s = "abcde", then it will be "bcdea" after one shift.
//

    public boolean rotateString(String s, String goal) {
        if(s.length()!=goal.length()){
            return false;
        }
        return (s+s).contains(goal);
    }

}
