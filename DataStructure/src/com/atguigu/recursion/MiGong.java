package com.atguigu.recursion;

public class MiGong {

    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图
        int map[][] = new int[8][7];
        //使用1表示有墙
        //先将上下设为墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右设为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置障碍
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("地图的情况：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        //找路
        //setWay1(map, 1, 1);
        setWay2(map,2,2);

        System.out.println("小球走过，并标识过的地图的情况：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    //使用递归给小球找路
    //1.map表示地图
    //2.i，j表示从地图的那个位置出发
    //3.如果小球能到map[6][5]的位置证明走出迷宫
    //4.规定，map[i][j]=0时代表该点没走过，1代表走过，2代表走过而且可以走通，3代表为死路
    //5.小球寻路的策略为下->右->上->左
    public static boolean setWay1(int map[][], int i, int j) {
        if (map[6][5] == 2) {
            return true;//代表路已经找到
        } else {
            if (map[i][j] == 0) {//如果当前点为0代表没走过

                map[i][j] = 2;//假设能够走通
                //按照规则走
                if (setWay1(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay1(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay1(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay1(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    //这个点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    //换一种策略右->下->左->上
    public static boolean setWay2(int map[][], int i, int j) {
        if (map[6][5] == 2) {
            return true;//代表路已经找到
        } else {
            if (map[i][j] == 0) {//如果当前点为0代表没走过

                map[i][j] = 2;//假设能够走通
                //按照规则走
                if (setWay2(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay2(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay2(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    //这个点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }


}
