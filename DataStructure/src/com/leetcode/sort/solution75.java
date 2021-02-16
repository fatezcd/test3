package com.leetcode.sort;

import java.util.*;

public class solution75 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 0, 0, 1, 0, 2, 1};
        sortColors(nums);
       System.out.println(Arrays.toString(nums));

    }

    public static void sortColors(int[] nums) {
        int zero = -1, one = 0, two = nums.length;
        while (one < two) {
            if (nums[one] == 2) {
                swap(nums, --two, one);
            } else if (nums[one] == 0) {
                swap(nums, ++zero, one++);
            } else {
                one++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

}
