package com.atguigu.sort;

import java.util.Arrays;

public class MergetSort {
    public static void main(String[] args) {
        //int arr[] = {8, 4, 5, 4, 1, 3, 6, 2};
        //定义一个数组，有80000个随机数
        int arr[] = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);//生成[0,800000)里的随机数
        }
        int temp[] = new int[arr.length];
       // System.out.println("排序前：" + Arrays.toString(arr));
        //System.out.println("排序前：" + Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        mergeSort(arr,0,arr.length-1,temp);
        long endTime = System.currentTimeMillis();
        long usedTime = (endTime - startTime);
        //System.out.println("排序后：" + Arrays.toString(arr));
        System.out.println("耗时" + usedTime + "毫秒");

        //System.out.println("排序后：" + Arrays.toString(arr));


    }

    //分加合方法
    public static void mergeSort(int arr[], int left, int right, int temp[]) {
        if (left < right) {
            int mid = (left + right) / 2;//中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    //合并的方法

    /**
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  作为中转的数组
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; //初始化i，左边有序序列的初始索引
        int j = mid + 1; //初始化j,右边有序序列的初始索引
        int t = 0; //指向temp数组的当前索引

        //先把左右两边的数据按照规则填充到temp数组
        //直到左右两边有一边处理完成
        while (i <= mid && j <= right) {//继续
            //如果左边的有序序列的当前元素小于等于右边有序序列的当前元素
            //将左边的元素添加到temp数组
            //然后i后移，t后移
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                //反之，将右边有序序列的当前元素填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }

        }

        //把有剩余数据的一边数据依次全部填充到temp
        while (i <= mid) {
            //左边序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right) {
            //右边序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //将temp数组的数据拷贝到arr
        //注意，不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft <=right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }
}
