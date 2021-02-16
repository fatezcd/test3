package com.atguigu.queue;

public class CircleArray {

    private int maxSize;//表示数组的最大容量
    private int front;//调整为指向队列的第一个元素
    private int rear;//调整为指向队列的最后一个元素的后一位，空出一个空间作为约定
    private int[] arr;//该数组用于模仿队列，存储数据


    //创建队列的构造器
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        front = 0;
        rear = 0;
        arr = new int[maxSize];
    }

    //判断队列是否已满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        //直接将数据加入
        arr[rear] = n;
        rear = (rear + 1) % maxSize;


    }

    //从队列中取数据
    public int getQueue() {
        //首先判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("该队列为空，没有数据");
        }
        //front是队列的第一个元素
        //1.先把front对应的变量保存到一个临时变量中
        //2.将front向后移
        //3.将临时保存的变量返回
        int temp = arr[front];
        front = (front + 1) % maxSize;
        return temp;
    }

    //显示队列的所有数据
    public void showQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("该队列为空，没有数据");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //判断环形队列中有多少个有效数据个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据，不是取出数据
    public int headQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("该队列为空，没有数据");
        }
        return arr[front];
    }

}
