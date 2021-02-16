package com.atguigu.linkedlist;

//测试
public class CircleSingleLinkedListDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
       circleSingleLinkedList.addBoy(10);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(2,3,10);//2,4,1,5,3
    }

}
