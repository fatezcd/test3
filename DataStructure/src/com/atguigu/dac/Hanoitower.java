package com.atguigu.dac;

public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }

    //汉诺塔的移动方法
    //使用分治算法
    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            //如果num>=2，分为两个盘：1.上面的所有盘为一个整体，2.最下面的一个盘
            //1.将上面的盘从a移动到b,中间需要借助c
            hanoiTower(num - 1, a, c, b);
            //2.把最下面的盘从a移动到c
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            //3.将上面的盘从b移动到c，中间需要借助a
            hanoiTower(num - 1, b, a, c);
        }
    }
}
