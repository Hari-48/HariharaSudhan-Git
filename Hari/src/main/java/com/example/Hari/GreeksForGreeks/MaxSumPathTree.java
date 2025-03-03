package com.example.Hari.GreeksForGreeks;

public class MaxSumPathTree {

        private static int maxSum;

        // Function to return the maximum path sum from any node in the tree.
        static int findMaxSum(Node node) {
            maxSum = Integer.MIN_VALUE; // Initialize to lowest possible value
            findMaxPathSum(node);
            return maxSum;
        }

        private static int findMaxPathSum(Node node) {
            if (node == null) {
                return 0;
            }

            // Calculate maximum path sum for left and right subtrees
            int left = Math.max(0, findMaxPathSum(node.left));  // Ignore negative sums
            int right = Math.max(0, findMaxPathSum(node.right));

            // Compute the max path sum that includes the current node
            int currSum = node.data + left + right;

            // Update the global maxSum if the current path sum is greater
            maxSum = Math.max(currSum, maxSum);

            // Return the maximum single path (either left + node or right + node)
            return Math.max(node.data + left, node.data + right);
        }

        public static void main(String[] args) {
            Node root = new Node(10);
            root.left = new Node(2);
            root.right = new Node(10);
            root.left.left = new Node(20);
            root.left.right = new Node(1);
            root.right.right = new Node(-25);
            root.right.right.left = new Node(3);
            root.right.right.right = new Node(4);

//            ClosestPoint solution = new ClosestPoint();
            System.out.println("Maximum Path Sum: " + findMaxSum(root));
        }
    }


