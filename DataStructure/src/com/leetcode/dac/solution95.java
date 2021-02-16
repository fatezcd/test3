package com.leetcode.dac;

import java.util.ArrayList;
import java.util.List;

public class solution95 {
    public static void main(String[] args) {
        List<TreeNode> treeNodes = generateTrees(3);
        System.out.println(treeNodes);
    }
    public static List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if(n == 0){
            return res;
        }
        return generateSubtrees(1,n);

    }
    //start作为根节点，end作为结束
    private static List<TreeNode> generateSubtrees(int start, int end){
        List<TreeNode> res = new ArrayList<TreeNode>();
        if(start > end){
            //不存在，将null加入结果中
            res.add(null);
            return res;
        }
        if(start == end){
            //只有一个数字，将当前数字作为一棵树加入结果中
            TreeNode tree = new TreeNode(start);
            res.add(tree);
            return res;
        }
        for(int i = start; i <=end; i++){
            //得到所有可能的左子树
            List<TreeNode> leftTree = generateSubtrees(start,i -1);
            //得到所有可能的右子树
            List<TreeNode> rightTree = generateSubtrees(i+1,end);
            for(TreeNode left : leftTree){
                for(TreeNode right : rightTree){
                    TreeNode root = new TreeNode(i);
                    root.left  = left;
                    root.right =  right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}