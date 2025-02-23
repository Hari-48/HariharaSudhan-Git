package com.example.Hari.GreeksForGreeks;
import java.util.PriorityQueue;

public class ClosestPoint {
    public int[][] kClosest(int[][] points, int k) {
        // Use a max heap (priority queue) to store the k closest points
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(distance(b), distance(a)) // Sort by distance in descending order
        );



        // Process each point
        for (int[] point : points) {
            maxHeap.offer(point); // Add to heap
            if (maxHeap.size() > k) {
                maxHeap.poll(); // Remove the farthest point to maintain the size
            }
        }



        // Collect the k closest points
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        return result;
    }

    // Helper function to calculate the squared distance from (0,0)
    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    // Testing the function
    public static void main(String[] args) {
        ClosestPoint sol = new ClosestPoint();
        int[][] points = { {1, 3}, {-2, 2}, {5, 8}, {0, 1} };
        int k = 2;
        int[][] closest = sol.kClosest(points, k);

        // Print result
        for (int[] point : closest) {
            System.out.println("[" + point[0] + ", " + point[1] + "]");
        }
    }
}

