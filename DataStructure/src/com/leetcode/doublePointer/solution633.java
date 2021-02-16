package com.leetcode.doublePointer;

public class solution633 {
    public static void main(String[] args) {
        int target = 9;
        boolean b = judgeSquareSum(9);
        System.out.println(b);
    }

    public static boolean judgeSquareSum(int target) {
        if (target < 0) {
            return false;
        }
        int i = 0;
        int j = (int) Math.sqrt(target);
        while (i <= j) {
            int res = i * i + j * j;
            if (res > target) {
                j--;
            } else if (res < target) {
                i++;
            } else {
                return true;
            }
        }
        return  false;
    }
}
