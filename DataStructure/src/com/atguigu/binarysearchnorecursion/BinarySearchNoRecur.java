package com.atguigu.binarysearchnorecursion;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int arr[] = {1, 8, 8, 11 , 67, 100};
        int index = binarySearch(arr, 8);
        System.out.println("index=" + index);
    }

    /**
     * 非递归的二分查找
     * @param arr 带查找的数组，arr是升序排序
     * @param target 需要查找的树
     * @return 返回对应的下标，没有返回-1
     */
    public static int binarySearch(int arr[], int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {//说明没有找到继续
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;//向左继续查找
            } else {
                left = mid + 1;//向右继续查找
            }

        }
        return -1;
    }
}
