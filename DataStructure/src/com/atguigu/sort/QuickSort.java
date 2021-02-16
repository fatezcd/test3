package com.atguigu.sort;

import java.util.Arrays;

public class QuickSort {

    private static void quickSort(int arr[], int left, int right) {
        int pivot = 0;
        if (left < right) {
            pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }


    private static int partition(int arr[], int left, int right) {
        int key = arr[left];
        while (left < right) {

            while (left < right && arr[right] >= key) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= key) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = key;
        return left;
    }

    public static void main(String[] args) {

         int arr[] = {65, 58, 95, 10, 57, 65, 13, 23, 78, 23, 85};
        //定义一个数组，有80000个随机数
        //int arr[] = new int[80000];
       // for (int i = 0; i < arr.length; i++) {
        //    arr[i] = (int) (Math.random() * 800000);//生成[0,800000)里的随机数
        //}
        //System.out.println("排序前：" + Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long endTime = System.currentTimeMillis();
        long usedTime = (endTime - startTime);
        //System.out.println("排序后：" + Arrays.toString(arr));
        System.out.println("耗时" + usedTime + "毫秒");


    }
}
