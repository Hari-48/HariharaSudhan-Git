package com.example.Hari.GreeksForGreeks;

import java.util.HashSet;
import java.util.Set;

public class DetectLoopLinkedList {

    public static boolean detectLoop(Node head) {

        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null){

            slow = slow.next;
            fast=fast.next.next;

            if(slow==fast){
                return true;
            }
        }
        return false;


    }


}
