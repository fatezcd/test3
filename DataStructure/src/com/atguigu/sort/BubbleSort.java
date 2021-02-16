package com.atguigu.sort;


public class BubbleSort {
    public static void main(String[] args) {
        // int arr[] = {3, 9, -1, 10, 20};

        //定义一个数组，有80000个随机数
        int arr[] = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);//生成[0,800000)里的随机数
        }

        // System.out.println("排序前：");
        //System.out.println(Arrays.toString(arr));

        //测试冒泡排序的速度O(n^2),80000个数据排序
        long startTime = System.currentTimeMillis();
        bubbleSort(arr);
        long endTime = System.currentTimeMillis();
        long usedTime = (endTime - startTime) / 1000;
        System.out.println("耗时" + usedTime + "秒");

        //System.out.println("排序后：");
        //System.out.println(Arrays.toString(arr));

        //为了更容易理解，我们把冒泡排序的演变过程，给大家演示

       /* int temp;

        //第一趟排序
        for (int i = 0; i < arr.length-1; i++) {
            //如果前面的数比后面的数大，则交换
            if (arr[i]>arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1]=temp;
            }
        }

        System.out.println("第一趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length-1-1; i++) {
            //如果前面的数比后面的数大，则交换
            if (arr[i]>arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1]=temp;
            }
        }

        System.out.println("第二趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length-1-2; i++) {
            //如果前面的数比后面的数大，则交换
            if (arr[i]>arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1]=temp;
            }
        }

        System.out.println("第三趟排序后的数组");
        System.out.println(Arrays.toString(arr));
         for (int i = 0; i < arr.length-1-2; i++) {
            //如果前面的数比后面的数大，则交换
            if (arr[i]>arr[i+1]){
                temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1]=temp;
            }
        }

        System.out.println("第四趟排序后的数组");
        System.out.println(Arrays.toString(arr));

        */

    }

    //封装成方法
    public static void bubbleSort(int arr[]) {
        //优化
        //定义一个临时变量，交换时使用
        int temp;
        //定义一个标识，记录是否发生交换
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                }
            }

            //System.out.printf("第%d次排序:%s\n",i+1,Arrays.toString(arr));
            //不再交换
            if (!flag)
                break;//直接退出循环
            else {
                flag = false;//重置标志，用于下次判断
            }
        }

    }

}
