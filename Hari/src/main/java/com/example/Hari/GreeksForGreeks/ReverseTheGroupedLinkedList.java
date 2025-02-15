package com.example.Hari.GreeksForGreeks;

public class ReverseTheGroupedLinkedList {


    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

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
        if (head == null || 3 <= 1) {
            return head; // No need to reverse if the list is empty or group size is 1
        }

        Node current = head;
        Node prevGroupTail = null;
        Node newHead = null;

        while (current != null) {
            // Reverse the next `k` nodes
            Node groupPrev = null;
            Node groupHead = current;

            int count = 0;
            while (current != null && count < 3) {
                Node nextNode = current.next;
                current.next = groupPrev;
                groupPrev = current;
                current = nextNode;
                count++;
            }

            // If it's the first group, set the new head of the list
            if (newHead == null) {
                newHead = groupPrev;
            }

            // Connect the previous group's tail to the current group's head
            if (prevGroupTail != null) {
                prevGroupTail.next = groupPrev;
            }
            // Update the previous group's tail to the current group's original head
            prevGroupTail = groupHead;
        }
        return newHead;
    }


}
