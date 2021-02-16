package com.leetcode.sort;

import java.util.PriorityQueue;

public class solution215 {
    public static void main(String[] args) {
        int nums[] = new int[]{2, 1, 4, 33, 23, 44};

        int kthLargest = findKthLargest(nums, 3);
        System.out.println(kthLargest);
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 小顶堆
        for (int val : nums) {
            pq.add(val);
            if (pq.size() > k)  // 维护堆的大小为 K
                pq.poll();
        }
        return pq.peek();
    }

}
