package com.leetcode.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class solution127 {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        String []words = {"hot","dot","dog","lot","log","cog"};
        for (String s:words) {
            wordList.add(s);

        }
        int i = ladderLength(beginWord, endWord, wordList);
        System.out.println(i);

    }
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        // visited 修改为 boolean 数组
        boolean[] visited = new boolean[wordList.size()];
        int idx = wordList.indexOf(beginWord);
        if (idx != -1) {
            visited[idx] = true;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int count = 0;
        while (queue.size() > 0) {
            int size = queue.size();
            ++count;
            while (size-- > 0) {
                String start = queue.poll();
                for (int i = 0; i < wordList.size(); ++i) {
                    // 通过 index 判断是否已经访问
                    if (visited[i]) {
                        continue;
                    }
                    String s = wordList.get(i);
                    if (!canConvert(start, s)) {
                        continue;
                    }
                    if (s.equals(endWord)) {
                        return count + 1;
                    }
                    visited[i] = true;
                    queue.offer(s);
                }
            }
        }
        return 0;
    }

    public static boolean canConvert(String s1, String s2) {
        // 因为题目说了单词长度相同，可以不考虑长度问题
        // if (s1.length() != s2.length()) return false;
        int count = 0;
        for (int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                ++count;
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }

}
