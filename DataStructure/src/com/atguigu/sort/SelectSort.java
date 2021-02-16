package com.atguigu.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
       // int[] arr = {101, 34, 5, 33, 21};
        //定义一个数组，有80000个随机数
        int arr[] = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);//生成[0,800000)里的随机数
        }
       /* System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        selectSort(arr);*/
        //测试选择排序的速度O(n^2),80000个数据排序
        long startTime = System.currentTimeMillis();
        selectSort(arr);
        long endTime = System.currentTimeMillis();
        long usedTime = (endTime - startTime);
        System.out.println("耗时" + usedTime + "毫秒");


    }

    //选择排序
    //选择排序的时间复杂度为O(n^2)
    public static void selectSort(int arr[]) {

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            //查找最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    //说明假设的最小值，并不是最小
                    min = arr[j];//重置min
                    minIndex = j;//重置minIndex
                }

            }
            //将最小值放在arr[i]，即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            //System.out.println("第" + (i + 1) + "轮后的：");
            //System.out.println(Arrays.toString(arr));
        }
    }
}
