package com.atguigu.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArray CircleArrayQueue = new CircleArray(4);
        char key = ' ';//用户输入字符
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取数据");
            System.out.println("h(head):查看队列的头数据");
            key = scanner.next().charAt(0);//接受一个字符
            switch (key) {
                case 's':
                    CircleArrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    try {
                        System.out.println("请输入要添加的数据:");
                        int value = scanner.nextInt();
                        CircleArrayQueue.addQueue(value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        int res = CircleArrayQueue.getQueue();
                        System.out.printf("取出的数据为%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'h':
                    try {
                        int head = CircleArrayQueue.headQueue();
                        System.out.printf("队列的头数据为%d\n", head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }

}
