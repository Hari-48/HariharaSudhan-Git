package com.example.Hari.GreeksForGreeks;

public class LongestPalindromeSubsequence {
    public static void main(String[] args) {
        String s = "bbabcbcab";
        System.out.println(longestPalindromeSubseq(s));
    }


//    public static int longestPlain(String s) {
//        int start =0;
//        int end =0 ;
//        for(int i=0;i<s.length();i++){
//            int odd = expand(s,i,i);
//            int even = expand(s,i,i+1);
//
//
//            int len = Math.max(odd,even);
//
//            if(len > end-start){
//                start = i- (len-1)/2;
//                end = i+len/2;
//            }
//        }
//        String string = s.substring(start,end+1);
//        System.out.println(string);
//        return string.length();
//    }
//
//    private static int expand(String s, int left, int right) {
//
//        while (left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
//            left--;
//            right++;
//
//        }
//        return right-left-1;
//
//    }

    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Base case: Single-character substrings
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Fill the DP table
        for (int len = 2; len <= n; len++) {  // Substring lengths
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];  // The longest subsequence for the entire string

}



}
