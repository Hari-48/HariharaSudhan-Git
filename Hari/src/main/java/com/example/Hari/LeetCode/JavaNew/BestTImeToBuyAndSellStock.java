package com.example.Hari.LeetCode.JavaNew;

public class BestTImeToBuyAndSellStock {
    public static void main(String[] args) {
        int[] prices = {4,7,1,2};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {

//        int max = 0;
//        for (int i = 0; i < prices.length; i++) {
//            for (int j = i + 1; j < prices.length; j++ ) {
//                int diff = prices[j]-prices[i];
//                if (diff > max) {
//
//                        max = diff;
//
//                }
//            }
//        }
//        return max;

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price; // update the minimum price
            } else {
                int profit = price - minPrice;
                if (profit > maxProfit) {
                    maxProfit = profit; // update max profit if it's higher
                }
            }
        }
        return maxProfit;
    }
}
