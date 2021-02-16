package com.atguigu.recursion;

public class queen8 {
    //定义max表示皇后的数量
    static int max = 8;
    //定义array数组表示皇后放置位置的结果
    static int array[] = new int[max];
    //定义count为解法数量
    static int count = 0;
    static int judgeCount;

    public static void main(String[] args) {
        queen8 queen8 = new queen8();
        queen8.check(0);
        System.out.printf("一共有%d种解法\n", count);
        System.out.printf("一共判断冲突%d次\n", judgeCount);
    }

    //放置皇后
    private void check(int n) {
        //八个皇后放好退出递归
        if (n == max) {
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //把第n个皇后，放入第一列
            array[n] = i;
            //判断是否冲突
            if (judge(n)) {
                //如果不冲突接着放n+1个皇后，即开始递归
                check(n + 1);
            }
            //如果冲突，就继续执行array[n]=i，将第n个皇后放在本行的下一列

        }

    }


    //判断是否冲突
    public static boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //判断第n个皇后是否和前面n-1个皇后是否在同一列 array[i]==array[n]
            //判断第n个皇后是否和前面n-1个皇后是否是否在同一斜线上 Math.abs(n-i)==Math.abs(array[n]-array[i])
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //格式化输出
    public void print() {
        count++;//成功计数加一
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");

        }
        System.out.println();
    }

}
