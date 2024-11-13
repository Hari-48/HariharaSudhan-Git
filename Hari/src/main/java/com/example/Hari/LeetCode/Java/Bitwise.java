package com.example.Hari.LeetCode.Java;

import java.util.Arrays;

public class Bitwise {

    public static void main(String[] args) {
        int[] candidates = {16, 17, 71, 62, 12, 24, 14};


        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int candidate : candidates) {
                if ((candidate & (1 << i)) != 0) cnt++;
            }
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);


    }


}
