package com.example.Hari.GreeksForGreeks;

import java.util.*;

public class LevelOrderTraversal {


    public ArrayList<ArrayList<Integer>> levelOrder(Node root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        Queue<Node> q= new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            ArrayList<Integer> currLevel = new ArrayList<>();
            while (size-->0){
                Node curr = q.poll();
                currLevel.add(curr.data);
                if(curr.left!=null)q.offer(curr.left);
                if(curr.right!=null)q.offer(curr.right);
            }
            result.add(currLevel);
        }
        return result;
    }




    
}
