package com.atguigu.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
       // int[] arr = {101, 34, 119, 1};
        //定义一个数组，有80000个随机数
        int arr[] = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);//生成[0,800000)里的随机数
        }
        /*System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        insertSort(arr);*/
        long startTime = System.currentTimeMillis();
        insertSort(arr);
        long endTime = System.currentTimeMillis();
        long usedTime = (endTime - startTime);
        System.out.println("耗时" + usedTime + "毫秒");

    }

    //插入排序
    public static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        //使用for循环简化代码
        for (int i = 1; i < arr.length; i++) {
            //定义插入的数
             insertVal = arr[i];
             insertIndex = i - 1;

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //判断是否赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

           // System.out.println("第" + i + "轮插入后：");
            // System.out.println(Arrays.toString(arr));
        }

        /*//第一轮
        //定义插入的数
        int insertVal = arr[1];
        int insertIndex = 1 - 1;//即arr[1]的前面这个数的下标

        //给insertVal找到插入的位置
        //说明
        //1.insertIndex >=0 保证给insertVal找插入位置，不越界
        //2.insertVal < arr[insertIndex]待插入的数，还没有找到插入的位置
        //3.就需要将arr[insertIndex]后移
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        //当退出while循环时，说明插入的位置找到了，insertIndex+1
        arr[insertIndex + 1] = insertVal;
        System.out.println("第一轮插入后：");
        System.out.println(Arrays.toString(arr));

        //第二轮
        insertVal = arr[2];
        insertIndex = 2 - 1;

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        arr[insertIndex + 1] = insertVal;
        System.out.println("第二轮插入后：");
        System.out.println(Arrays.toString(arr));

        //第三轮
        insertVal = arr[3];
        insertIndex = 3 - 1;

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }

        arr[insertIndex + 1] = insertVal;
        System.out.println("第三轮插入后：");
        System.out.println(Arrays.toString(arr));*/


    }
}
