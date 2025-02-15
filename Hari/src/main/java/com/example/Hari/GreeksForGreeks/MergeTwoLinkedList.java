package com.example.Hari.GreeksForGreeks;

import com.sun.source.tree.WhileLoopTree;
import lombok.val;

public class MergeTwoLinkedList {

    public static void main(String[] args) {

        Node head1 = new Node(10);
        head1.next = new Node(20);
        head1.next.next = new Node(30);
        head1.next.next.next = new Node(40);


        Node head2 = new Node(5);
        head2.next = new Node(15);
        head2.next.next = new Node(25);
        head2.next.next.next = new Node(35);


        sorted(head1,head2);




        // Print the linked list
//        printLinkedList(head1);
//        printLinkedList(head2);
    }

    public static void printLinkedList(Node head) {
        Node current = head;

        // Print original linked list
        System.out.print("Original Linked List: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }



    public static Node sorted(Node head1, Node head2) {

        Node mergedNode = new Node(0);
        Node curr = mergedNode;


        //check whether two is null
        while (head1 != null && head2 != null) {

            // Compare two head1 and head2 of first element
            if (head1.data <= head2.data) {
                // if head1 is smallest ;-

                //move that value into cuurent node
                curr.next=head1;
                // change head1 to ( forward the position) head1 of first elemet
                head1=head1.next;
            }
            else
            {
                curr.next=head2;
                head2=head2.next;
            }

            // now store the current value to Curr
            curr=curr.next;
        }

        curr.next=head1!=null?head1:head2;
        printLinkedList(mergedNode.next);
        return mergedNode.next;

    }









}
