package com.leetcode.greedyAlgorithm;

public class solution53 {
    public static void main(String[] args) {
        int nums[] = {-2,-3,-1,-2};
        int i = maxSubArray(nums);
        System.out.println(i);
    }
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int preSum = nums[0];
        int maxSum = preSum;
        for (int i = 1; i < nums.length; i++) {
            preSum = preSum > 0 ? preSum + nums[i] : nums[i];
            maxSum = Math.max(maxSum, preSum);
        }
        return maxSum;
    }
}
