package com.leetcode.Backtracking;

import java.util.ArrayList;
import java.util.List;

public class solution131 {
    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> partition = partition(s);
        System.out.println(partition);
    }
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> track = new ArrayList<>();

        backtrack(s,track,res);

        return res;
    }
    private static void backtrack(String s, List<String> track, List<List<String>> res){
        if(s.length()==0){
            res.add(new ArrayList<>(track));
            return;
        }
        for(int i = 0;i < s.length();i++){
            if(checkPalindrome(s,0,i)){
                track.add(s.substring(0, i + 1));
                backtrack(s.substring(i+1),track,res);
                track.remove(track.size()-1);

            }
        }
    }
    private static boolean checkPalindrome(String str,int left,int right){
        while(left < right){
            if(str.charAt(left)!=str.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
