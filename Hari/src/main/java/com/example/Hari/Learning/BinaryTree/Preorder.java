package com.example.Hari.Learning.BinaryTree;// Definition for a binary tree node


// Binary tree class
class Preorder {
    Node root;

    // Function to print preorder traversal
    public static void printPreorder(Node node) {
        if (node == null)
            return;

        // Print the node's data
        System.out.print(node.data + " ");

        // Recur on left subtree
        printPreorder(node.left);

        // Recur on right subtree
        printPreorder(node.right);
    }

    public static void main(String[] args) {
        Preorder tree = new Preorder();

        // Constructing the binary tree
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.right = new Node(6);

        // Function call
        System.out.println("Preorder traversal of binary tree is: ");
        printPreorder(tree.root);
    }
}
