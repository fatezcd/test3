package com.atguigu.dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {
        int w[] = {1, 4, 3};//物品的重量
        int val[] = {1500, 3000, 2000};//物品的价值
        int m = 5;//背包的容量
        int n = val.length;//物品的个数
        //创建二维数组
        //v[i][j]表示在前i个物品中能够装入容量为j的背包的最大价值
        int v[][] = new int[n + 1][m + 1];
        //为了记录放入商品的情况，我们定一个二维数组
        int path[][] = new int[n + 1][m + 1];

        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//将第一列设置为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//将第一行设置为0
        }
        //动态规划
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    // 当准备加入新增的商品的容量大于当前背包的容量时，就直接使用上一个
                    //单元格的装入策略
                    v[i][j] = v[i - 1][j];

                } else {
                    // 当 准备加入的新增的商品的容量小于等于当前背包的容量
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前的记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        //输出一下 v 看看目前的情况
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j = j - w[i - 1];
            }
            i--;
        }

    }
}
