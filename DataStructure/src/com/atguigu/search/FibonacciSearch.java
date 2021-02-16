package com.atguigu.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int index = fibSearch(arr, 1000);
        if (index == -1) {
            System.out.println("没有找到");

        } else {
            System.out.println("找到了，下标为" + index);
        }
    }

    //因为后面我们mid = low+F(k-1)-1,需要使用到斐波那契数列
    //使用非递归的方法得到一个斐波那契数列
    public static int[] fib() {
        int f[] = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 2] + f[i - 1];
        }
        return f;
    }

    public static int fibSearch(int a[], int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid;
        int f[] = fib();
        //获取斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]的值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        //不足的部分用0填充
        int temp[] = Arrays.copyOf(a, f[k]);
        //实际上需求使用a数组最后的数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        //使用while循环处理，找到我们的数key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {//向数组的前面查找
                high = mid - 1;
                //为什么是k--
                //1.全部元素=前面的元素+后面的元素
                //2.f[k] = f[k-1]+f[k-2]
                //因为前面有f[k-1]个元素，所以可以继续拆分为f[k-1]=f[k-2]+f[k-3]
                //即在f[k-1]的前面继续查找k--
                //即下次循环mid=f[k-1-1]-1
                k--;

            } else if (key > temp[mid]) {//向数组的后面查找
                low = mid + 1;
                //为什么是k-=2
                //1.全部元素=前面的元素+后面的元素
                //2.f[k] = f[k-1]+f[k-2]
                //因为后面有f[k-2]个元素，所以可以继续拆分为f[k-1]=f[k-3]+f[k-4]
                //即在f[k-2]的前面继续查找k-=2
                //即下次循环mid=f[k-1-2]-1
                k -= 2;

            } else {
                //需要确定，返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
