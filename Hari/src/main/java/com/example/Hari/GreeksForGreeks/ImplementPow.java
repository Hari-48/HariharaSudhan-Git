package com.example.Hari.GreeksForGreeks;

public class ImplementPow {


    public static void main(String[] args) {
       Double ans = power( -0.67000,-7);
        System.out.println(ans);
    }


    public  static  double power(double b, int e) {

        double ans = 1;
        if (e < 0) {
            b = 1 / b;  // Take the reciprocal of the base
            e = -e;     // Make the exponent positive
        }


        for(int i= 0 ; i<e ;  i++){
            System.out.println(i);
            ans=ans*b;
        }
        return Math.round(ans * 100000.0) / 100000.0;
    }


//    boolean isNegativeExponent = (e < 0);
//    double ans = 1.0;
//
//    if (isNegativeExponent) {
//        b = 1 / b;  // Take the reciprocal of the base
//        e = -e;     // Make the exponent positive
//    }
//
//    while (e > 0) {
//        if ((e & 1) == 1) {  // If e is odd
//            ans *= b;
//        }
//        b *= b;   // Square the base
//        e >>= 1;  // Divide exponent by 2
//    }
//
//    return Math.round(ans * 100000.0) / 100000.0;


}
