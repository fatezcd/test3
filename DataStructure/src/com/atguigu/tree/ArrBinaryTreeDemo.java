package com.atguigu.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
        arrBinaryTree.infixOrder();
        arrBinaryTree.postOrder();
    }

}

class ArrBinaryTree {
    private int[] arr;//储存数据结点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载此方法
    public void preOrder() {
        this.preOrder(0);
    }
    public void infixOrder() {
        this.infixOrder(0);
    }
    public void postOrder() {
        this.postOrder(0);
    }

    public void preOrder(int index) {
        //如果数组为空，或者数组的长度为0
        if (arr.length == 0 || arr == null) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }

        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        //向右递归遍历
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }

    }
    public void infixOrder(int index) {
        //如果数组为空，或者数组的长度为0
        if (arr.length == 0 || arr == null) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }

        //向左递归遍历
        if ((2 * index + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向右递归遍历
        if ((2 * index + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }

    }
    public void postOrder(int index) {
        //如果数组为空，或者数组的长度为0
        if (arr.length == 0 || arr == null) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //向左递归遍历
        if ((2 * index + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        //向右递归遍历
        if ((2 * index + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        //输出当前这个元素
        System.out.println(arr[index]);

    }
}
