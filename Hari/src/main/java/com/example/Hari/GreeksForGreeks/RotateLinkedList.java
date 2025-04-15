package com.example.Hari.GreeksForGreeks;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.List;

public class RotateLinkedList {


    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = new Node(50);

        System.out.println("Original Linked List:");
        printLinkedList(head);

        int k = 4;
        //  head = rotate(head, k);
        head = rotates(head, k);

        System.out.println("Rotated Linked List:");
        printLinkedList(head);
    }


    public static Node rotates(Node head, int k) {
        int len = 1;

        Node tmp = head;
        while (tmp.next != null) {
            len++;
            tmp = tmp.next;
        }


        k = k % len;


        if (k == 0)
            return head;


        tmp.next = head;
        tmp = head;
        for (int i = 1; i < k; i++) {
            tmp = tmp.next;
        }
        head = tmp.next;
        tmp.next = null;
        return head;
    }


    // Rotate the linked list `k` times
    public static Node rotate(Node head, int k) {
        if (head == null || k <= 0) {
            return head; // No need to rotate an empty list or zero rotations
        }

        // Convert linked list to ArrayLissst for easier manipulation
        List<Integer> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }

        // Rotate the list
        for (int i = 0; i < k; i++) {
            Integer swap = list.remove(0); // Remove the first element
            list.add(swap); // Add it to the end
        }

        // Rebuild the linked list from the rotated list
        Node newHead = null;
        Node tail = null;
        for (int value : list) {
            Node newNode = new Node(value);
            if (newHead == null) {
                newHead = newNode; // Set the first node as the new head
                tail = newHead;
            } else {
                tail.next = newNode;
                tail = tail.next;
            }
        }

        return newHead; // Return the rotated linked list
    }

    // Print the linked list
    public static void printLinkedList(Node head) {
        System.out.print("Linked List: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }


}


//    public static void main(String[] args) {
//
//        Node head = new Node(10);
//        head.next = new Node(20);
//        head.next.next = new Node(30);
//        head.next.next.next = new Node(40);
//        head.next.next.next.next = new Node(50);
//
//
//        int k = 4;
//
////        printLinkedList(head,k);
//        rotate(head, k);
//
//    }
//
//    public static void rotate(int k) {
//        if (head == null || k <= 0) {
//            return; // No need to rotate an empty list or zero rotations
//        }
//
//        // Convert linked list to ArrayLissst for easier manipulation
//        List<Integer> list = new ArrayLissst<>();
//        Node current = head;
//        while (current != null) {
//            list.add(current.data);
//            current = current.next;
//        }
//
//        // Rotate the list
//        for (int i = 0; i < k; i++) {
//            Integer swap = list.remove(0); // Remove the first element
//            list.add(swap); // Add it to the end
//        }
//
//        // Clear the current linked list and rebuild it
//        head = null;
//        addToNode(list); // Recreate the linked list from the rotated list
//    }
//
//
//
//    public void addToNode(List<Integer> list) {
//        if (list == null || list.isEmpty()) {
//            return; // No elements to add
//        }
//        for (int num : list) {
//            add(num);
//        }
//    }
//
//    // Print the linked list
//    public void printLinkedList() {
//        System.out.print("Linked List: ");
//        Node current = head;
//        while (current != null) {
//            System.out.print(current.data + " -> ");
//            current = current.next;
//        }
//        System.out.println("null");
//    }
//
//
//
//
//







/*

    public static void printLinkedList(Node head, int k) {
        Node current = head;

        // Print original linked list
        System.out.print("Original Linked List: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");

    }

    public static void rotate(Node head, int k) {


        Node current = head;

        Node heads = null;


        List<Integer> list = new ArrayLissst<>();


        while (current != null) {
            list.add(current.data);
            current = current.next;
        }

        for (int i = 0; i < k; i++) {
            Integer swap = list.remove(0);
            list.add(swap);
            System.out.println(list);
        }

        System.out.println(list);
    }


    public void addToNode(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        for (int num : list) {
            add(num); // Add each element to the linked list
        }
    }

    // Add a single value to the linked list
    public void add(int value) {
        Node heads = null;
        if (heads == null) {
            heads = new Node(value);
        } else {
            Node current = heads;
            while (current.next != null) {
                current = current.next; // Traverse to the end of the list
            }
            current.next = new Node(value); // Add the new node at the end
        }
    }

    // Print the linked list
    public void printList() {
        Node heads = null;
        Node current = heads;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    */


//        Node prev = null; // Initially, the reversed list is empty
//        Node current = head;
//
//        while (current != null) {
//            Node nextNode = current.next; // Save the next node
//            current.next = prev;         // Reverse the link
//            prev = current;              // Move `prev` one step forward
//            current = nextNode;          // Move `current` one step forward
//        }
//
//        return prev; /





