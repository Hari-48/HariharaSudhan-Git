package com.example.Hari.GreeksForGreeks;

import java.util.ArrayList;

public class KthSmallestInBST {


    public static void main(String[] args) {

    }

    public static  int kthSmallest(Node root, int k) {

        ArrayList<Integer> ls = new ArrayList<>();
        inOrder(root,ls);
        if(k>0 && k<= ls.size()){
            return ls.get(k-1);
        }
        return -1;

        // Write your code here
    }

    private static void inOrder(Node root, ArrayList<Integer> ls) {
        if(root==null){
            return;
        }
        inOrder(root.left,ls);
        ls.add(root.data);
        inOrder(root.right,ls);
    }


}
