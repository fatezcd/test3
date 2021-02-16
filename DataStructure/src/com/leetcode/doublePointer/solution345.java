package com.leetcode.doublePointer;

import java.util.Arrays;
import java.util.HashSet;

public class solution345 {
    private final static HashSet<Character> vowels = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
    public static void main(String[] args) {
        String s = "hello";
        String s1 = reverseVowels(s);
        System.out.println(s1);

    }

    public static String reverseVowels(String s){
        if(s==null){
            return null;
        }
        int i = 0,j = s.length()-1;
        char res[] = new char[s.length()];
        while (i<=j){
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if(!vowels.contains(ci)){
                res[i++] = ci;
            }else if(!vowels.contains(cj)){
                res[j--]=cj;
            }else {
                res[i++] = cj;
                res[j--] = ci;
            }
        }
        return new String(res);
    }
}
