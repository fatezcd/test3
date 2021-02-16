package com.atguigu.stack;

import java.util.Scanner;

public class SingleLinkedListStackDemo {
    public static void main(String[] args) {
        //测试
        SingleLinkedListStack LinkedListstack = new SingleLinkedListStack(3);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈(入栈)");
            System.out.println("pop:表示从栈中取出数据(出栈)");
            System.out.println("请输入你的选择:");
            key = scanner.next();
            switch (key) {
                case "show":
                    LinkedListstack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数:");
                    int value = scanner.nextInt();
                    LinkedListstack.push(value);
                    break;
                case "pop":
                    try {
                        LinkedListstack.pop();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
