package com.leetcode.greedyAlgorithm;

import java.util.Arrays;
import java.util.Comparator;


public class solution435 {
    public static void main(String[] args) {
        int intervals[][] = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int i = eraseOverlapIntervals(intervals);
        System.out.println(i);
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        //按区间的结尾进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
                //return (o1[1] < o2[1]) ? -1 : ((o1[1] == o2[1]) ? 0 : 1);
            }
        });
        int cnt = 1; //不重叠的区间个数，至少为1
        int end = intervals[0][1]; //第一个区间结尾
        for (int i = 1; i < intervals.length; i++) {
            //和前一个区间重叠，跳过
            if (intervals[i][0] < end) {
                continue;
            }
            end = intervals[i][1];//结尾后移
            cnt++;
        }
        return intervals.length - cnt; //区间总个数减去不重叠区间的个数
    }

}
