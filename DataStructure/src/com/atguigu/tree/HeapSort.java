package com.atguigu.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        //int arr[] = {4, 6, 8, 5, 9,-3,56,33};
        //定义一个数组，有80000个随机数
        int arr[] = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);//生成[0,800000)里的随机数
        }
        long startTime = System.currentTimeMillis();
        heapSort(arr);
        long endTime = System.currentTimeMillis();
        long usedTime = (endTime - startTime);
        System.out.println("耗时" + usedTime + "毫秒");

    }

    //编写堆排序的方法
    public static void heapSort(int arr[]) {
        int temp = 0;
        System.out.println("堆排序：");

        //分部完成
        /*adjustHeap(arr, 1, arr.length);
        System.out.println("第一次" + Arrays.toString(arr));

        adjustHeap(arr, 0, arr.length);
        System.out.println("第二次" + Arrays.toString(arr));*/

        //将无序序列构建成一个堆，根据升序还是降序选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //将堆顶元素与末尾元素交换，将最大元素沉到数组末端
        //重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换，直到整个序列有序
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        //System.out.println("数组=" + Arrays.toString(arr));
    }

    //将一个数组（二叉树），调整为一个大顶堆

    /**
     * 功能： 完成将以i对应的非叶子节点的树调整为大顶堆
     * 举例 int arr[] = {4,6,8,5,9} =>i=1=>adjustHeap=>4,9,8,5,6
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中索引
     * @param length 表示对多少个元素继续调整，length是逐渐在减少
     */
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i];//先取出当前元素的值，保存在临时变量中
        //开始调整
        //k=i*2+1 k是i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                //说明左子节点的值小于右子节点的值
                k++;//k指向右子节点
            }
            if (arr[k] > temp) {//子节点的大于父节点
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k;// i指向k，继续循环比较
            } else {
                break;
            }
        }
        //for循环结束后，我们已经将以i为父节点的树的最大值，放在最顶部（底部）
        arr[i] = temp;//将temp值放到调整后的位置
    }
}
