package com.atguigu.search;

import java.util.ArrayList;

public class InsertValueSearch {
    public static void main(String[] args) {
       /* int arr[] = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }*/
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        int index = insertValueSearch(arr, 0, arr.length - 1, 10);
        //int index = binarySearch(arr, 0, arr.length - 1, 10);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到了，下标为：" + index);
        }
    }

    //二分查找
    public static int binarySearch(int arr[], int left, int right, int findVal) {
        System.out.println("二分查找次数...");
        //当left>right时，说明没有找到
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {//向右递归
            return binarySearch(arr, mid + 1, right, findVal);

        } else if (findVal < midVal) {//向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }

    //插值查找算法
    public static int insertValueSearch(int arr[], int left, int right, int findVal) {
        System.out.println("插值查找次数...");
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        //求出mid,自适应
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}


