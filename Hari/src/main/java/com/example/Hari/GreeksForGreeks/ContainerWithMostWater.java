package com.example.Hari.GreeksForGreeks;



public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] arr = {2, 1, 8, 6, 4, 6, 5, 5};
        Integer result = maxWater(arr);
        System.out.println(result);
    }

    public static int maxWater(int arr[]) {
        int water = 0;
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = Math.min(arr[left], arr[right]) * (right - left);
            water = Math.max(water, temp);
            if (arr[left] > arr[right]) right--;
            else left++;
        }
        return water;
    }


//        int res = 0;
//
//
//
//        int lastIndex =arr[arr.length-1];
//        int highest =0;
//        int highestIndex = 0;
//
//
//
//        int newTemp=0;
//
//        int newTemp1=0;
//
//
//        for(int i=0;i<arr.length-2;i++){
//
//            int distance = arr.length-1-i;
//            int min = Math.min(arr[i],arr[arr.length-1]);
//
//            newTemp=min*distance;
//            newTemp1=Math.max(0,newTemp);
//
//
//
//
//        }
//
//
////        for (int i = 0; i < arr.length; i++) {
////            if (arr[i] == highest) {
////                highestIndex=i;
////            }
////        }
////
////
////        int distance = arr.length-1-highestIndex;
////        int min = Math.min(highest,lastIndex);
////        res=min*distance;
////
//
//
//
//
//
//        return newTemp1;


}
