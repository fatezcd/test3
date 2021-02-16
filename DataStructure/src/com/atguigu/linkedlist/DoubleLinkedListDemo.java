package com.atguigu.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        //创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "公孙胜", "入云龙");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        System.out.println("最初的链表:");
        doubleLinkedList.list();

        doubleLinkedList.delete(1);
        System.out.println("删除后的链表:");
        doubleLinkedList.list();

        HeroNode2 newHero = new HeroNode2(1,"小宋","及时雨~");
        doubleLinkedList.update(newHero);
        System.out.println("修改后的链表:");
        doubleLinkedList.list();




    }


}
