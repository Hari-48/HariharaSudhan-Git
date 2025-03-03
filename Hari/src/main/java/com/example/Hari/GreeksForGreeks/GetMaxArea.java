package com.example.Hari.GreeksForGreeks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class GetMaxArea {

    public static void main(String[] args) {
        int[] arr = {60, 20, 50, 40, 10, 50, 60};
        System.out.println(getMaxArea(arr));


    }


    public static int getMaxArea(int arr[]) {
        int n = arr.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i =0;i<+n;i++){
            int curHeight = i == n?0:arr[i];
            while(!stack.isEmpty() && curHeight<arr[stack.peek()]){
                int top = stack.pop();
                int width = stack.isEmpty()?1:i-stack.peek()-1;
                int area = arr[top]*width;
                maxArea=Math.max(area,maxArea);
            }
            stack.push(i);
        }
        return maxArea;
    }


/*
    public static int getMaxArea(int arr[]) {

      int n =arr.length;

      int maxArea = 0;
      int[] left = new int[n];
      int[] right = new int[n];
      left[0]=-1;
      right[n-1]=n;

      for ( int i =1; i<n ;i++){
          int prev = i-1;
          while(prev>=0 && arr[prev]>=arr[i]){
                  prev=left[prev];
          }
          left[i]=prev;
      }
      Arrays.stream(left).forEach(System.out::print);


      for(int i = n-2 ; i>=0; i--){
          int prev = i+1;
          while(prev< n && arr[prev]<=arr[i]){
              prev=right[prev];
          }
          right[i] =prev;
      }

      for(int i =0 ; i<n ; i++){
          int width = right[i]-left[i]-1;
          maxArea=Math.max(maxArea,arr[i]*width);
      }


      System.out.println(maxArea);
      return maxArea;


    }

 */
}
