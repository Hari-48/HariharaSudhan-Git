package com.example.Hari.GreeksForGreeks;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


//class Node {
//    int data;
//    Node next;
//
//    Node(int data) {
//        this.data = data;
//        this.next = null;
//    }
//}

public class ReverseTheLinkedList {
    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        // Print the linked list
        printLinkedList(head);
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

        // Reverse the linked list
        Node reversedHead = reverseLinkedList(head);


        // Print reversed linked list
        System.out.print("Reversed Linked List: ");
        Node reversedCurrent = reversedHead;
        while (reversedCurrent != null) {
            System.out.print(reversedCurrent.data + " -> ");
            reversedCurrent = reversedCurrent.next;
        }
        System.out.println("null");
    }

    public static Node reverseLinkedList(Node head) {
        Node prev = null; // Initially, the reversed list is empty
        Node current = head;

        while (current != null) {
            Node nextNode = current.next; // Save the next node
            current.next = prev;         // Reverse the link
            prev = current;              // Move `prev` one step forward
            current = nextNode;          // Move `current` one step forward
        }

        return prev; // `prev` becomes the new head of the reversed list
    }


//    Node reverseList(Node head) {
//
//
//        Node prev = null; // Initially, the reversed list is empty
//        Node current = head;
//
//        while (current != null) {
//            Node nextNode = current.next; // Save the next node
//            current.next = prev;         // Reverse the link
//            prev = current;              // Move `prev` one step forward
//            current = nextNode;          // Move `current` one step forward
//        }
//        return prev;
//
//    }
//













}