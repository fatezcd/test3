package com.atguigu.stack;


//定义一个单链表
public class SingleLinkedList {

    //头节点
    private Node head = new Node(-1);

    //根据参数创建链表
    public void createLinkedList(int maxSize) {
        if (maxSize < 1) {
            System.out.println("输入的参数有误");
            return;
        }
        Node temp = head;
        for (int i = 0; i < maxSize; i++) {
            Node node = new Node(i);
            temp.setNext(node);
            temp = node;
        }
    }

    //将top作为指针指向栈顶
    public void add(int top, int data) {

        Node temp = head;
        //将指针移动到链表最后
        for (int i = 0; i < top + 1; i++) {
            temp = temp.getNext();
        }
        //添加数据
        temp.setData(data);
    }

    //
    public int getHead(int top) {
        Node temp = head;
        for (int i = 0; i < top; i++) {
            temp = temp.getNext();
        }
        //将数据取出
        int data = temp.getNext().getData();
        temp = temp.getNext().getNext();
        return data;
    }

    //遍历链表
    public void list(int top) {
        Node temp = head;
        while (top >= 0) {
            for (int i = 0; i < top + 1; i++) {
                temp = temp.getNext();
            }
            System.out.printf("stack[%d]=%d\n", top, temp.getData());
            // 栈顶往下移动一次
            top--;
            // 指针还原到头节点
            temp = head;
        }

    }

}
