package com.atguigu.search;

import java.util.ArrayList;

public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int index = binarySearch(arr, 0, arr.length - 1, 1000);
        //ArrayList<Integer> index = binarySearch2(arr, 0, arr.length - 1, 1000);
        if (index == 0) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到了，下标为：" + index);
        }


    }

    //二分查找
    public static int binarySearch(int arr[], int left, int right, int findVal) {
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

    //含有重复数字的二分查找
    public static ArrayList<Integer> binarySearch2(int arr[], int left, int right, int findVal) {
        //当left>right时，说明没有找到
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {//向右递归
            return binarySearch2(arr, mid + 1, right, findVal);

        } else if (findVal < midVal) {//向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            //找到mid索引值后，不要马上返回
            ArrayList<Integer> resIndexlist = new ArrayList<>();
            //向mid索引值的左边扫描，将所有满足1000的元素的下标，加入的集合ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {//退出
                    break;
                }
                //否则，就temp放入resIndexlist
                resIndexlist.add(temp);
                temp--;
            }
            resIndexlist.add(mid);
            //向mid索引值的右边扫描，将所有满足1000的元素的下标，加入的集合ArrayList
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resIndexlist.add(temp);
                temp++;
            }

            return resIndexlist;

        }

    }

}
