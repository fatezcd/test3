package com.atguigu.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {

        //int arr[] = {53, 3, 542, 48, 14, 214};

        //定义一个数组，有80000个随机数
        int arr[] = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);//生成[0,800000)里的随机数
        }
        //System.out.println("排序前：" + Arrays.toString(arr));
        long startTime = System.currentTimeMillis();
        radixSort(arr);
        long endTime = System.currentTimeMillis();
        long usedTime = (endTime - startTime);
        //System.out.println("排序后：" + Arrays.toString(arr));
        System.out.println("耗时" + usedTime + "毫秒");



    }

    //基数排序
    public static void radixSort(int[] arr) {

        //最终代码
        //1.得到数组中最大数的位数
        //假设第一个数最大
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i])
                max = arr[i];
        }
        //得到最大数的位数

        int maxLenghth = (max + "").length();


        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1.二维数组包含10个一维数组
        //2.为了防止数据溢出，每个一维数组的长度为arr.length
        //3.基数排序使用的是用空间换取时间的经典算法
        int bucket[][] = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据，我们定义了一个一维数组来记录各个桶中每次放入的数据的个数
        //比如：bucketElementCounts[0],记录的就是bucket[0]桶中放入的元素的个数
        int bucketElementCounts[] = new int[10];

        //这里我们使用循环
        for (int i = 0, n = 1; i < maxLenghth; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个位数
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
            int index = 0;
            //遍历每一个桶，并将桶中的数据，放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {

                //如果桶中有数据，我们才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶，放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //把bucketElementCounts清零
                bucketElementCounts[k] = 0;

            }


          //  System.out.println("第" + (i + 1) + "轮，对个位数进行排序后:" + Arrays.toString(arr));
        }

        /*
        //第一轮，对每个元素的个位数进行排序处理
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位数
            int digitOfElement = arr[j] % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
        int index = 0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {

            //如果桶中有数据，我们才放入到原数组
            if (bucketElementCounts[k] != 0) {
                //循环该桶，放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入arr
                    arr[index++] = bucket[k][l];
                }
            }
            //把bucketElementCounts清零
            bucketElementCounts[k] = 0;

        }


        System.out.println("第一轮，对个位数进行排序后:" + Arrays.toString(arr));


        //====================================================

        //第二轮，对每个元素的十位数进行排序处理
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的十位数
            int digitOfElement = arr[j] / 10 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
        index = 0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {

            //如果桶中有数据，我们才放入到原数组
            if (bucketElementCounts[k] != 0) {
                //循环该桶，放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入arr
                    arr[index++] = bucket[k][l];
                }
            }

            bucketElementCounts[k] = 0;

        }

        System.out.println("第二轮，对十位数进行排序后:" + Arrays.toString(arr));


        //====================================================

        //第三轮，对每个元素的百位数进行排序处理
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的百位数
            int digitOfElement = arr[j] / 100 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
        index = 0;
        //遍历每一个桶，并将桶中的数据，放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {

            //如果桶中有数据，我们才放入到原数组
            if (bucketElementCounts[k] != 0) {
                //循环该桶，放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入arr
                    arr[index++] = bucket[k][l];
                }
            }

            bucketElementCounts[k] = 0;

        }

        System.out.println("第三轮，对百位数进行排序后:" + Arrays.toString(arr));

         */


    }

}
