package com.atguigu.linkedlist;


//定义SingleLinkedList管理我们的英雄
public class SingleLinkedList {
    //先初始化一个头节点，头节点不要动，不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }
    //添加节点到链表
    //，当不考虑编号的顺序时
    //1.先遍历链表找到最后的节点
    //2.将最后的节点的next指向新的节点

    //直接加到链表的最后
    public void add(HeroNode heroNode) {
        //头节点不能动，因此我们需要一个辅助变量
        HeroNode temp = head;
        while (true) {
            //遍历链表，找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有到最后就一直向下移
            temp = temp.next;
        }
        //跳出while循环证明已经到链表的最后
        temp.next = heroNode;
    }

    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，所以需要一个辅助变量来遍历
        HeroNode temp = head;
        while (true) {
            //判断链表是否到最后
            if (temp.next == null) {
                break;
            }
            //将temp后移
            temp = temp.next;
            //输出链表信息
            System.out.println(temp);

        }

    }

    //按照顺序添加
    public void addByOrder(HeroNode heroNode) {
        //头节点不能动，所以仍然通过一个辅助变量来帮助我们找到添加的位置
        //因为是单链表，我们找到的temp是要添加位置的前一个节点

        HeroNode temp = head;
        boolean flag = false;//flag标志要添加的编号是否已经存在，默认为false
        //遍历链表，寻找要插入的位置
        while (true) {

            //temp已经到链表的最后
            if (temp.next == null) {
                break;
            }
            //找到要插入的位置
            if (temp.next.no > heroNode.no) {
                break;
            }
            if (temp.next.no == heroNode.no) {
                //说明要添加的heroNode的编号已经存在
                flag = true;
            }
            //将temp后移
            temp = temp.next;
        }

        if (flag) {
            System.out.printf("准备添加的英雄编号%d已经存在,无法再添加\n", heroNode.no);
        } else {
            //添加节点到指定位置
            heroNode.next = temp.next;
            temp.next = heroNode;
        }

    }

    //修改节点
    public void update(HeroNode newhHeroNode) {
        //根据编号修改节点的信息
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("该链表为空");
            return;
        }
        HeroNode temp = head.next;
        //是否找到要修改的节点
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newhHeroNode.no) {
                //找到要修改的节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        //修改节点
        if (flag) {
            temp.name = newhHeroNode.name;
            temp.nickname = newhHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号为%d的节点，无法修改\n", newhHeroNode.no);
        }

    }

    //删除节点
    //1.head不能动，通过辅助变量temp找到要删除的节点
    //2.比较temp.next.no和要删除节点的no
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;//是否找到要删除的节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //删除节点
            temp.next = temp.next.next;
        } else {
            System.out.println("要删除的节点不存在");
        }
    }


}
