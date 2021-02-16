package com.atguigu.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        //int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        //定义一个数组，有80000个随机数
        int arr[] = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);//生成[0,800000)里的随机数
        }
       // System.out.println("排序前：" + Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        //shellSort(arr);//交换式
        shellSort2(arr);//移位式
        long endTime = System.currentTimeMillis();
        long usedTime = (endTime - startTime);
        System.out.println("耗时" + usedTime + "毫秒");


    }

    //希尔排序  交换法
    public static void shellSort(int[] arr) {

        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("希尔排序第" + (++count) + "轮：" + Arrays.toString(arr));


       /* //第一轮，将10个数据分成了10/2=5 组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中的所有元素（共5组，每组2个元素），步长为5
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于加上步长后的元素，交换
                if (arr[j] > arr[j + 5]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("希尔排序第1轮后：" + Arrays.toString(arr));

        //第二轮，将10个数据分成了5/2=2 组
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中的所有元素（共2组，每组5个元素），步长为2
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前元素大于加上步长后的元素，交换
                if (arr[j] > arr[j + 2]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("希尔排序第2轮后：" + Arrays.toString(arr));

        //第三轮，将10个数据分成了2/2=1 组
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中的所有元素（共2组，每组5个元素），步长为2
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前元素大于加上步长后的元素，交换
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序第3轮后：" + Arrays.toString(arr));*/
        }

    }

    //对交换式希尔排序进行优化>位移法
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //步长gap，并逐步的缩小增量
            for (int i = gap; i < arr.length; i++) {
                //从第gap个元素，逐个对其所在的组进行直接插入排序
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while循环后，就给temp找到了插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
}
