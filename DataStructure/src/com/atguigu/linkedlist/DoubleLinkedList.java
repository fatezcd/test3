package com.atguigu.linkedlist;

//创建一个双向链表
public class DoubleLinkedList {
    //先初始化一个头节点，头节点不要动，不存放具体数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //遍历链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //头节点不能动，定义一个辅助变量帮助我们遍历链表
        HeroNode2 temp = head;
        while (temp.next != null) {

            temp = temp.next;
            System.out.println(temp);
        }
    }

    //向链表中添加节点(默认添加到双向链表最后)
    public void add(HeroNode2 heroNode2) {

        //遍历链表,直到最后
        HeroNode2 temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode2;
        heroNode2.pre = temp;

    }

    //修改节点的内容,思路和单向链表一样
    public void update(HeroNode2 newhHeroNode2) {
        //根据编号修改节点的信息
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("该链表为空");
            return;
        }
        HeroNode2 temp = head;
        //是否找到要修改的节点
        boolean flag = false;
        while (temp.next != null) {

            if (temp.no == newhHeroNode2.no) {
                //找到要修改的节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //修改节点
        if (flag) {
            temp.name = newhHeroNode2.name;
            temp.nickname = newhHeroNode2.nickname;
        } else {
            System.out.printf("没有找到编号为%d的节点，无法修改\n", newhHeroNode2.no);
        }

    }

    //删除链表中的某一节点
    //因为是双向链表，所以可以实现自我删除
    public void delete(int no) {
        //先遍历链表找到要删除的节点
        HeroNode2 temp = head;
        boolean flag = false;
        while (temp.next != null) {
            if (temp.no == no)
                flag = true;
            temp = temp.next;
        }
        if (flag){
            temp.pre.next = temp.next;
            //如果是删除最后一个节点，执行下面的程序，否则会出现空指针异常
            if(temp.next!=null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("要删除的%d节点不存在\n",no);
        }
    }


}
