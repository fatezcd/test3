package com.atguigu.linkedlist;


import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");
        //创建链表
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        SingleLinkedList singleLinkedList3 = new SingleLinkedList();
        //将节点添加到链表
      /*  singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);*/

        //按照编号顺序添加
        singleLinkedList1.addByOrder(hero1);
        //singleLinkedList.addByOrder(hero3);
        //singleLinkedList.addByOrder(hero2);
        singleLinkedList1.addByOrder(hero3);
        singleLinkedList2.addByOrder(hero2);
        singleLinkedList2.addByOrder(hero4);


        //修改节点
       /* HeroNode newHero = new HeroNode(1,"小宋","及时雨~");
        singleLinkedList.update(newHero);
*/
        //删除节点
       /* singleLinkedList.delete(1);
        System.out.println("删除后的链表：");*/
        System.out.println("原来的链表1：");
        singleLinkedList1.list();
        System.out.println("原来的链表2：");
        singleLinkedList2.list();
        System.out.println("合并后的链表：");
        HeroNode combine = combine(singleLinkedList1.getHead(), singleLinkedList2.getHead());
        singleLinkedList3.add(combine);
        singleLinkedList3.list();

        //System.out.println(getLength(singleLinkedList.getHead()));

        //System.out.println(findLastIndexNode(singleLinkedList.getHead(), 3));
       /* System.out.println("反转后的链表:");
        reverseList(singleLinkedList.getHead());*/
      /*  System.out.println("逆序打印单链表：");
        reversePrint(singleLinkedList.getHead());*/

        // singleLinkedList.list();

    }

    //获取单个链表的节点个数（如果是带头结点的链表，需求不统计头节点）
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;//空链表
        }

        int length = 0;
        HeroNode temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    //查找单向链表中的倒数第k个节点
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断链表是否为空
        if (head.next == null) {
            return null;
        }
        //遍历链表得到链表的长度
        int size = getLength(head);

        //遍历到size-index的位置，就是倒数第index个节点
        //判断index
        if (index <= 0 || index > size) {
            return null;
        }
        //定义辅助变量，for循环定位到倒数第index位置
        HeroNode temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //将链表反转
    public static void reverseList(HeroNode head) {
        //判断链表是否为空或者只有一个节点
        if (head.next == null || head.next.next == null) {
            return;
        }

        //定义一个辅助变量，帮助我们遍历原来的链表
        HeroNode cur = head.next;//先暂时保存当前节点的后一节点，后面会用到
        HeroNode next = null;//指向当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");//新建一个头节点
        //遍历原来的节点，每遍历一个节点添加到新的头节点的后面
        while (cur != null) {
            next = cur.next;
            cur.next = reverseHead.next;//将节点插入到新建头节点后面
            reverseHead.next = cur;
            cur = next;//将当前节点后移
        }
        //将头节点换过来
        head.next = reverseHead.next;

    }

    //将链表倒序打印
    //利用栈这个数据结构，将各个节点压入栈中，然后利用栈的先进后出的特点，实现倒序打印
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //将栈中的节点弹出打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());//栈的特点是先入后出
        }
    }

    //合并两个有序的单链表，合并之后的链表仍然有序
    public static HeroNode combine(HeroNode head1, HeroNode head2) {


        //判断两个链表都不能为空
        if (head1.next == null && head1.next == null) {
            System.out.println("链表为空");
        }
        //合并之后的链表的头节点
        HeroNode combineHead = new HeroNode(0, "", "");
        //定义一个辅助变量,用来遍历合并后的链表
        HeroNode combineTemp = combineHead;
        //定义辅助变量，帮助我们遍历原来的链表
        HeroNode cur1 = head1.next;
        HeroNode cur2 = head2.next;
        //如果链表1为空直接返回链表2
        if (head1.next == null) {
            combineHead.next = head2.next;
        }
        //如果链表2为空直接返回链表1
        if (head2.next == null) {
            combineHead.next = head1.next;
        }

        while (cur1 != null && cur2 != null) {
            //比较链表1和链表2的节点，将较小的节点添加到新的链表中
            if (cur1.no <= cur2.no) {
                combineTemp.next = cur1;
                combineTemp = combineTemp.next;
                cur1 = cur1.next;

            } else {
                combineTemp.next = cur2;
                combineTemp = combineTemp.next;
                cur2 = cur2.next;

            }
        }
        //当链表1遍历完后，将链表2剩余的节点添加到新的链表后
        if (cur1 == null) {
            while (cur2 != null) {
                combineTemp.next = cur2;
                combineTemp = combineTemp.next;
                cur2 = cur2.next;
            }
        }
        //当链表2遍历完后，将链表1剩余的节点添加到新的链表后
        if (cur2 == null) {
            while (cur1 != null) {
                combineTemp.next = cur1;
                combineTemp = combineTemp.next;
                cur1 = cur1.next;
            }
        }
        //返回合并后的头节点
        return combineHead.next;
    }


}



