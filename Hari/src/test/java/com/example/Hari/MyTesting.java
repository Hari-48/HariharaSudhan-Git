package com.example.Hari;

import com.example.Hari.JUnit.Calculator;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyTesting {


    @Test
    public void testAssert(){
        int num = 48;
        assertEquals(num,6*8);
        System.out.println("Both test numbers are same, test case Passed ");
    }

    @Test
    public void testAssertNot(){
        int num = 48;
        assertNotEquals(num,6*8+1);

        System.out.println("Both  numbers are not same, test case Passed ");
    }



    @Test
    public void addition(){
        int x= 4;
        int y = 6;
        int result = Calculator.addition(x,y);
        assertEquals(10, result);
        System.out.println("Assumption is correct");
    }

























}
