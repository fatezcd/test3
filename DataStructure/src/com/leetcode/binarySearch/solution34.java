package com.leetcode.binarySearch;

public class solution34 {
    public static void main(String[] args) {
        int nums[] = {5,7,7,8,8,10};
        int target = 9;
        int[] ints = searchRange(nums, target);
        System.out.println(ints);
    }
    public static int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        int last = findFirst(nums, target + 1) - 1;
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        } else {
            return new int[]{first, Math.max(first, last)};
        }
    }

    private static int findFirst(int[] nums, int target) {
        int l = 0, h = nums.length; // 注意 h 的初始值
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= target) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}
