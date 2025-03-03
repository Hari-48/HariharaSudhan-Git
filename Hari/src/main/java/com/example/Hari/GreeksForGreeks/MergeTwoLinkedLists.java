package com.example.Hari.GreeksForGreeks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeTwoLinkedLists {

    public static void main(String[] args) {

        // First linked list: 5 -> 10 -> 15
        Node head1 = new Node(5);
        head1.next = new Node(10);
        head1.next.next = new Node(15);

        // Second linked list: 2 -> 3 -> 20
        Node head2 = new Node(2);
        head2.next = new Node(3);
        head2.next.next = new Node(20);
        
        List<Node> nodes = new ArrayList<>();
        nodes.add(head1);
        nodes.add(head2);
        
        

        Node res = sortedMerge(nodes);
        printList(res);
    }



    private static Node sortedMerge(List<Node> nodes) {
        ArrayList<Integer> res =  new ArrayList<>();
        for(Node node : nodes){
            while(node!=null){
                res.add(node.data);
                node = node.next;
            }
        }
        Collections.sort(res);
        Node dummy = new Node(-1);
        Node current = dummy;
        for (Integer re : res) {
            current.next = new Node(re);
            current = current.next;

        }
        return dummy.next;
    }












    static void printList(Node curr) {
        while (curr != null) {
            System.out.print(curr.data);
            if (curr.next != null) {
                System.out.print(" ");
            }
            curr = curr.next;
        }
        System.out.println();
    }
}
