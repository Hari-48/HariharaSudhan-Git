package com.example.Hari.Learning.BinaryTree;

public class HeightOfTheTree {

    static int height(Node node) {
        if (node == null) {
            return -1; // Base case: empty tree has height 0
        }

        // Recursively get height of left and right subtrees
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        // Height of tree is max(leftHeight, rightHeight) + 1
        return Math.max(leftHeight, rightHeight) + 1;

    }

    public static void main(String[] args)
    {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);
        root.right.right.right = new Node(7);


        System.out.println(height(root));
    }





}
