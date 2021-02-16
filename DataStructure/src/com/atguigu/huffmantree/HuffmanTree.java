package com.atguigu.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
      int arr[] = {13,7,8,3,29,6,1};
      Node root = createHuffmanTree(arr);

      //测试
        preOrder(root);

    }
public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else {
            System.out.println("空树！");
        }
}

    //创建霍夫曼树的方法

    /**
     * @param arr 需要创建为霍夫曼树的数组
     * @return 返回创建好霍夫曼树的根节点
     */
    public static Node createHuffmanTree(int arr[]) {
        //遍历arr数组
        //将arr数组中的每个元素转变为Node节点
        //将Node放入arraylist中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            //从小到大排序
            Collections.sort(nodes);

            System.out.println("node=" + nodes);

            //取出根节点权值最小的两颗二叉树
            //取出权值最小的节点（二叉树）
            Node leftNode = nodes.get(0);
            //取出权值第二小的节点（二叉树）
            Node rightNode = nodes.get(1);

            //新建一二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //从集合中删掉处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入到nodes
            nodes.add(parent);
        }
        //返回霍夫曼树的root节点
        return nodes.get(0);
    }
}


//创建节点类
//为了让NOde对象持续排序Collections集合排序
//让Node实现Comparable接口
class Node implements Comparable<Node> {
    int value;//节点的权值
    Node left;//左子节点
    Node right;//右子节点

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
