package com.example.Hari.GreeksForGreeks;

public class MirrorTree {


    public static void main(String[] args) {


        // Representation of input binary tree 1
        //        1
        //       / \
        //      3   2
        //          / \
        //         5   4

        Node root1 = new Node(1);
        root1.left = new Node(3);
        root1.right = new Node(2);
        root1.right.left = new Node(5);
        root1.right.right = new Node(4);

        convertIntoMirror(root1);



        // Representation of input binary tree 2 (mirror)
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5
        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        root2.left.left = new Node(4);
        root2.left.right = new Node(5);

//        if (areMirrors(root1, root2)) {
//            System.out.println("true");
//        }
//        else {
//            System.out.println("false");
//        }

    }

    static boolean areMirrors(Node root1, Node root2) {

        // If both roots are empty, they are mirrors
        if (root1 == null && root2 == null) {
            return true;
        }

        // If only one root is empty, they are not mirrors
        if (root1 == null || root2 == null) {
            return false;
        }

        // Check if the root data is the same and
        // if the left subroot of root1 is a mirror
        // of the right subroot of root2 and vice versa
        return (root1.data == root2.data) &&
                areMirrors(root1.left, root2.right) &&
                areMirrors(root1.right, root2.left);
    }


    static Node convertIntoMirror(Node node){
        if (node == null) {
            return null;
        }
        Node leftMirror= convertIntoMirror(node.left);
       Node rightMirror =convertIntoMirror(node.right);
       node.right =leftMirror;
       node.left = rightMirror;
        System.out.println(node.data);
        return node;

    }



    void mirror(Node node) {
        // Base case: If node is null, return
        if (node == null) {
            return;
        }

        // Recursively convert left and right subtrees
        mirror(node.left);
        mirror(node.right);

        // Swap left and right subtrees
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
    }
}
