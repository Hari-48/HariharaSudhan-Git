package com.example.Hari.GreeksForGreeks;

// refer binary tree package


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






}
