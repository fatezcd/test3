package com.leetcode.doublePointer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class solution524 {
    public static void main(String[] args) {
        String s = "abpcplea";
        String ss[] = new String[]{"ale","apple","monkey","plea"};
        List<String> d = new LinkedList<String>();
        for (int i = 0; i < ss.length; i++ ){

            d.add(ss[i]);
        }

        String longestWord = findLongestWord(s, d);
        System.out.println(longestWord);
    }
    public static String findLongestWord(String s, List<String> d) {
        String longestWord = "";
        for (String target : d) {
            int l1 = longestWord.length(), l2 = target.length();
            if (l1 > l2 || (l1 == l2 && longestWord.compareTo(target) < 0)) {
                continue;
            }
            if (isSubstr(s, target)) {
                longestWord = target;
            }
        }
        return longestWord;
    }

    private static boolean isSubstr(String s, String target) {
        int i = 0, j = 0;
        while (i < s.length() && j < target.length()) {
            if (s.charAt(i) == target.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == target.length();
    }
}
