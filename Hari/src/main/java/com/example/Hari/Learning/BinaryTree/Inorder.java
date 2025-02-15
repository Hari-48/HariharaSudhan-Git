package com.example.Hari.Learning.BinaryTree;

import java.util.ArrayList;

public class Inorder {
    public static void printInorder(Node node)
    {
        if (node == null)
            return;

        // First recur on left subtree
        printInorder(node.left);

        // Now deal with the node
        System.out.print(node.data + " ");

        // Then recur on right subtree
        printInorder(node.right);
    }




   public static ArrayList<Integer> inOrder(Node root) {

        ArrayList<Integer> ans = new ArrayList<>();


        {
            if (root == null)
                return ans;

            // First recur on left subtree
            inOrder(root.left);

            // Now deal with the node
            System.out.print(root.data + " ");
            ans.add(root.data);

            // Then recur on right subtree
            inOrder(root.right);



            // Code
        }
       System.out.println(ans);
        return ans;
    }








    // Driver code
    public static void main(String[] args)
    {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        // Function call
        System.out.println(
                "Inorder traversal of binary tree is: ");
        System.out.println(inOrder(root));
    }
}
