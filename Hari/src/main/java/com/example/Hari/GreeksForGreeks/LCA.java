package com.example.Hari.GreeksForGreeks;

public class LCA {
    Node LCA(Node root, Node n1, Node n2) {
        // code here.
        return solve(root, Math.min(n1.data,n2.data), Math.max(n1.data,n2.data));
    }
    Node solve(Node root, int n1, int n2){
        if(root==null)
            return root;
        if((n1<root.data && n2>root.data) || root.data == n1 || root.data == n2){
            return root;
        }
        if(n2<root.data){
            return solve(root.left, n1, n2);
        }
        return solve(root.right, n1, n2);

    }


}
