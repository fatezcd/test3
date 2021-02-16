package com.atguigu.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台，放入Map
        HashMap<String, HashSet> broadcasts = new HashMap<String, HashSet>();
        //将各个电台放入broadcasts
        //将每个电台覆盖的地区添加到hashset中
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //将各个电台放入broadcasts
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas 存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        for (String key : broadcasts.keySet()) {
            HashSet hashSet = broadcasts.get(key);
            allAreas.addAll(hashSet);
        }

        //创建ArrayList集合来存放选择的电台
        ArrayList<String> selects = new ArrayList<>();
        //定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前未覆盖地区的交集
        HashSet<String> tempSet = new HashSet<>();
        //定义maxkey，保存在一次遍历过程中，覆盖最多未覆盖地区的电台对应的key
        //如果maxKey不为null，加入到selects
        String maxKey = null;
        while (allAreas.size() != 0) {
            //每次while，都要将maxkey置空
            maxKey = null;

            //遍历broadcasts，取出对应key
            for (String key : broadcasts.keySet()) {
                //每次for循环将tempset清空
                tempSet.clear();
                //当前电台覆盖的区域
                HashSet areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出areas和allareas的交集并赋给tempSet
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，
                // 比 maxKey 指向的集合地区还多,就需要重置 maxKey
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }

            if (maxKey != null) {
                selects.add(maxKey);
                //将maxkey指向的广播电台覆盖的区域，从broadcasts中删除
                allAreas.removeAll(broadcasts.get(maxKey));
            }

        }
        System.out.println("得到的选择结果是" + selects);
    }


}

