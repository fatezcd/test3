package com.atguigu.linkedlist;

//创建一个环形的单向链表
public class CircleSingleLinkedList {
    //创建一个first节点
    private Boy first = new Boy(-1);


    //添加节点，构建成一个环形的链表
    public void addBoy(int nums) {

        //通过一个辅助指针来完成添加
        Boy curBoy = null;
        //对nums做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        //使用for循环来添加节点
        for (int i = 1; i <= nums; i++) {
            //新建节点
            Boy boy = new Boy(i);
            //如果是第一个节点
            if (i == 1) {
                first = boy;
                first.setNext(first);//构成环
                curBoy = first;//指向第一个节点
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历环形链表
    public void showBoy() {
        //判断是否是空链表
        if (first == null) {
            System.out.println("该链表为空");
            return;
        }
        //因为first不能动，因此我们使用辅助指针来帮助遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("该小孩的编号为%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) {//说明已经遍历完
                break;
            }
            curBoy = curBoy.getNext();//后移辅助指针

        }
    }

    //根据输入，计算小孩出圈的顺序
    //startNo小孩开始报数的位置，countNum每隔几个小孩就出圈，nums一共有多少小孩
    public void countBoy(int startNo, int countNum, int nums) {
        //先对输入的数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误");
            return;
        }
        //定义一个辅助变量,指向first的前一个节点
        Boy helper = first;
        //将helper移动到链表最后
        while (true) {
            if (helper.getNext() == first)
                break;
            helper = helper.getNext();
        }

        //将helper和first移动到开始报数的位置，移动startNo-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //小孩开始报数，helper和first再移动countNum-1次
        //一直循环到圈中只剩一个节点
        while (true) {
            if (helper == first)//说明圈中只剩一个节点
                break;

            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的节点
            System.out.printf("编号为%d的小孩出圈\n", first.getNo());
            //出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩的编号为%d\n", first.getNo());

    }
}
