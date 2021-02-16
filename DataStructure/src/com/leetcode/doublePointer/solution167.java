package com.leetcode.doublePointer;

import java.util.Arrays;

public class solution167 {
    public static void main(String[] args) {
        int numbers[] = {5,25,75};
        int target = 100;
        int[] res = twoSum(numbers, target);
        System.out.println(Arrays.toString(res));
    }

    public static int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        if (numbers == null) {
            return null;
        }
        while (i < j) {
            int num = numbers[i] + numbers[j];
            if (num > target) {
                j--;
            } else if (num < target) {
                i++;
            } else {
                return new int[]{i + 1, j + 1};
            }
        }
        return null;
    }
}
