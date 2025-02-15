package com.example.Hari.GreeksForGreeks;

public class AddTwoLinkedList {


    public static void main(String[] args) {

        Node node1 = new Node(0);
        node1.next = new Node(4);
        node1.next.next = new Node(5);



        Node node2 = new Node(3);
        node2.next = new Node(4);
        node2.next.next = new Node(5);


        printLinkedList(node1);
        printLinkedList(node2);

      Node n=   addTwoLists(node1,node2);
//
//        printLinkedList(node1);


    }

    private static void printLinkedList(Node node1) {
        Node currentNode = node1;
        while (currentNode!=null){
            System.out.print(currentNode.data + " -> ");
            currentNode=currentNode.next;
        }
        System.out.println("null");
    }

    static Node addTwoLists(Node num1, Node num2) {
        // code here
        Node l1 = reverseList(num1);
        Node l2 = reverseList(num2);
        Node curr = null;
        int carry = 0;
        while(l1!=null || l2!=null || carry >0){
            int v1 = l1!=null ? l1.data : 0;
            int v2 = l2!=null ? l2.data : 0;
            int sum = v1+v2+carry;
            carry = sum/10;
            Node tmp = new Node(sum%10);
            tmp.next = curr;
            curr = tmp;
            if(l1!=null){
                l1=l1.next;
            }
            if(l2!=null){
                l2=l2.next;
            }
        }
        while(curr!=null && curr.data == 0){
            curr=curr.next;
        }
        return curr;

    }
    static Node reverseList(Node head) {
        // code here
        Node tmpHead = null;
        while(head!=null){
            Node nex = head.next;
            if(tmpHead == null) {
                tmpHead = head;
                tmpHead.next = null;
            }
            else {
                head.next = tmpHead;
                tmpHead = head;
            }
            head = nex;
        }
        return tmpHead;
    }
}

//
//
//    public static Node addTwoNodes(Node l1 , Node l2){
//
//
//
//        l1=reverseLinkedList(l1);
//        l2=reverseLinkedList(l2);
//
//
//
//        Node p = l1;
//        Node q = l2;
//        Node curr = null;
//        int carry =0;
//
//
//        while(p!=null || q!=null) {
//            int x = p != null ? p.data : 0;
//            int y = q != null ? q.data : 0;
//
//            int sum = x + y + carry;
//            carry = sum / 10;
//
//            Node temp = new Node(sum % 10);
//            temp.next = curr;
//            curr = temp;
//
//
////            curr.next=new Node(sum%10);
////            curr = curr.next;
//
//            if (p != null) {
//                p = p.next;
//            }
//            if (q != null) {
//                q = q.next;
//            }
//        }
//
//        if(carry>0){
//            curr.next=new Node(carry);
//        }
//
//
////        printLinkedList(curr);
//
//
//    while(curr!=null && curr.data==0){
//        curr=curr.next;
//    }
//
//
//
//return curr;
//
//
//        // Step 2: Reverse the final linked list sum
////        Node reversedSum = reverseLinkedList(dummy.next);
////
////        return reversedSum;
//    }
//
//    private static Node reverseLinkedList(Node head) {
//        Node prev = null;
//        Node current = head;
//
//        while (current != null) {
//            Node nextNode = current.next;
//            current.next = prev;
//            prev = current;
//            current = nextNode;
//        }
//        return prev;
//    }
//
//    // Helper method to convert a linked list to a number
//    private static int convertLinkedListToNumber(Node head) {
//        int number = 0;
//        int multiplier = 1;
//        Node current = head;
//
//        while (current != null) {
//            number += current.data * multiplier;
//            multiplier *= 10;
//            current = current.next;
//        }
//
//        return number;
//    }
//
//
//}
