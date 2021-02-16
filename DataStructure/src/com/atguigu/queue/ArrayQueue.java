package com.atguigu.queue;

//使用数组模拟队列，编写一个ArrayQueue类
public class ArrayQueue {
    private int maxSize;//表示数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数组用于模仿队列，存储数据


    //创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        front = -1;//指向队列头，front是队列头的前一个位置
        rear = -1;//指向队列尾，rear正好是队列尾
        arr = new int[arrMaxSize];
    }

    //判断队列是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        //如果队列头等于队列尾则证明队列为空
        return front == rear;
    }

    //向队列中添加数据
    public void addQueue(int n) {
        //首先判断队列是否已满
        if (isFull()) {
            throw new RuntimeException("该队列已满，无法再添加数据");
        }
        rear++;//队列尾后移
        arr[rear] = n;

    }

    //从队列中取数据
    public int getQueue() {
        //首先判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("该队列为空，没有数据");
        }
        front++;//队列头后移
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("该队列为空，没有数据");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //显示队列的头数据，不是取出数据
    public int headQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("该队列为空，没有数据");
        }
        return arr[front + 1];
    }

}
