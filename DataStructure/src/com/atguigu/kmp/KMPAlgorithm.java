package com.atguigu.kmp;


import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBCABCDABABCDABCDABDE";
        String str2 = "ABCDABD";
       // String str3 = "abababca";
        int next[] = kmpNext(str2);
        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" +index);

    }

    /**
     * kmp搜索算法
     *
     * @param str1 源字符串
     * @param str2 子字符串
     * @param next 字串对应的部分匹配表
     * @return 返回第一个匹配位置的下标，没有则返回-1
     */
    public static int kmpSearch(String str1, String str2, int next[]) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //核心
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取一个字符串的部分匹配值表
    //求next数组的过程完全可以看成字符串匹配的过程，
    //就是从模式字符串的第一位(注意，不包括第0位)开始对自身进行匹配运算。
    //在任一位置，能匹配的最长长度就是当前位置的next值。
    public static int[] kmpNext(String dest) {
        //创建一个next数组保存部分配置值
        int next[] = new int[dest.length()];
        //如果字符串的长度为1，部分匹配值为0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //如果dest.charAt(i)!=dest.charAt(j),我们从next[j-1]获取新的j
            //当发现有dest.charAt(i)==dest.charAt(j)退出
            //核心
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                //j = 0; //也可以
                j = next[j - 1];

            }
            //如果dest.charAt(i)==dest.charAt(j),部分匹配值加1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
