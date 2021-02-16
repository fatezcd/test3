package com.leetcode.doublePointer;

public class solution680 {
    public static void main(String[] args) {
        String s = "abcda";
        boolean b = validPalindrome(s);
        System.out.println(b);

    }

    public static boolean validPalindrome(String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i, j - 1) || isPalindrome(s, i + 1, j);
            }
        }
        return true;

    }

    public static boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
