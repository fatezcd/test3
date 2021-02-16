package com.atguigu.search;

import java.util.Scanner;

public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1, 34, 55, 322, 344, 22};
        System.out.println("请输入要查找的数：");
        Scanner sc = new Scanner(System.in);
        int value = sc.nextInt();
        int index = seqSearch(arr, value);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到了，下标为" + index);
        }
    }


    public static int seqSearch(int arr[], int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
